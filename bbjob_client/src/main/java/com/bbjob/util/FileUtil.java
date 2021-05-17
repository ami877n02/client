package com.bbjob.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.UUID;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class FileUtil {
	private static final int buffer = 1024;

	/**
	 * 检测网络资源是否存在
	 * 
	 * @param strUrl
	 * @return
	 */
	public static boolean isNetFileAvailable(String strUrl) {
		InputStream netFileInputStream = null;
		try {
			URL url = new URL(strUrl);
			URLConnection urlConn = url.openConnection();
			netFileInputStream = urlConn.getInputStream();
			if (null != netFileInputStream) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (netFileInputStream != null)
					netFileInputStream.close();
			} catch (IOException e) {
			}
		}
	}

	public static void clearPath(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				File tmpfile = files[i];
				if (tmpfile.isDirectory()) {
					clearPath(tmpfile);
				} else {
					tmpfile.delete();
				}
			}
			file.delete();
		}
	}

	public static String getFileEncoding(File file) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1)
				return charset;
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8";
				checked = true;
			}
			bis.reset();
			if (!checked) {
				int loc = 0;

				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF)
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF)
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}

			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}

	public static File zipFile(String outPath, String fileDir, String fileIncludes, String fileExcludes) {
		File f = new File(outPath);
		if (f.exists())
			f.delete();
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(f);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(new File(fileDir));
		fileSet.setIncludes(fileIncludes); // 包括哪些文件或文件夹
											// eg:zip.setIncludes("*.java");
		fileSet.setExcludes(fileExcludes);// 排除哪些文件或文件夹
		zip.addFileset(fileSet);
		zip.execute();
		return f;
	}

	public static File zipFile(String outPath, String fileDir, String fileIncludes) {
		return zipFile(outPath, fileDir, fileIncludes, null);
	}

	public static File zipFile(String outPath, String fileDir) {
		return zipFile(outPath, fileDir, null, null);
	}

	/**
	 * 解压zip文件
	 * 
	 * @param zipFilePath
	 * @param outputDirectory
	 *            输出目录
	 * @return
	 */
	public static boolean unzipFile(File zipFilePath, String outputDirectory) {
		boolean flag = false;
		String charset = FileUtil.getFileEncoding(zipFilePath);
		System.out.println(charset);
		try {
			ZipFile zipFile = new ZipFile(zipFilePath, charset);
			Enumeration e = zipFile.getEntries();
			ZipEntry zipEntry = null;
			createDirectory(outputDirectory, "");
			while (e.hasMoreElements()) {
				zipEntry = (ZipEntry) e.nextElement();
				String fileName = zipEntry.getName();
				System.out.println("unzip " + fileName);
				if (zipEntry.isDirectory()) {
					String name = fileName.trim();
					name = name.substring(0, name.length() - 1);
					File f = new File(outputDirectory + File.separator + name);
					if (!f.exists()) {
						f.mkdir();
					}

				} else {
					fileName = fileName.replace('\\', '/');
					if (fileName.indexOf("/") != -1) {
						createDirectory(outputDirectory, fileName.substring(0, fileName.lastIndexOf("/")));
						fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
					}
					File f = new File(outputDirectory + File.separator + fileName);
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);
					byte[] by = new byte[buffer];
					int c;
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					in.close();
					out.close();
				}
			}
			flag = true;
			System.out.println("unzip finished");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}

	public static String getMD5(File f) {
		BigInteger bi = null;
		try {
			byte[] buffer = new byte[8192];
			int len = 0;
			MessageDigest md = MessageDigest.getInstance("MD5");
			FileInputStream fis = new FileInputStream(f);
			while ((len = fis.read(buffer)) != -1) {
				md.update(buffer, 0, len);
			}
			fis.close();
			byte[] b = md.digest();
			bi = new BigInteger(1, b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi.toString(16);
	}

	private static void createDirectory(String directory, String subDirectory) {
		String dir[];
		File fl = new File(directory);
		try {
			if (subDirectory == "" && fl.exists() != true) {
				fl.mkdir();
			} else if (subDirectory != "") {
				dir = subDirectory.replace('\\', '/').split("/");
				for (int i = 0; i < dir.length; i++) {
					File subFile = new File(directory + File.separator + dir[i]);
					if (subFile.exists() == false) {
						subFile.mkdir();
					}
					directory += File.separator + dir[i];
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * 把指定文件或目录转换成指定的编码
	 * 
	 * @param file
	 *            要转换的文件或目录
	 * @param fromCharsetName
	 *            源文件的编码
	 * @param toCharsetName
	 *            要转换的编码
	 * @throws Exception
	 */
	public static void convert(File file, String fromCharsetName, String toCharsetName) throws Exception {
		convert(file, fromCharsetName, toCharsetName, null);
	}

	/**
	 * 把指定文件或目录转换成指定的编码
	 * 
	 * @param file
	 *            要转换的文件或目录
	 * @param fromCharsetName
	 *            源文件的编码
	 * @param toCharsetName
	 *            要转换的编码
	 * @param filter
	 *            文件名过滤器
	 * @throws Exception
	 */
	public static void convert(File file, String fromCharsetName, String toCharsetName, FilenameFilter filter)
			throws Exception {
		if (file.isDirectory()) {
			File[] fileList = null;
			if (filter == null) {
				fileList = file.listFiles();
			} else {
				fileList = file.listFiles(filter);
			}
			for (File f : fileList) {
				convert(f, fromCharsetName, toCharsetName, filter);
			}
		} else {
			if (filter == null || filter.accept(file.getParentFile(), file.getName())) {
				String fileContent = getFileContentFromCharset(file, fromCharsetName);
				saveFile2Charset(file, toCharsetName, fileContent);
			}
		}
	}

	/**
	 * 以指定编码方式读取文件，返回文件内容
	 * 
	 * @param file
	 *            要转换的文件
	 * @param fromCharsetName
	 *            源文件的编码
	 * @return
	 * @throws Exception
	 */
	public static String getFileContentFromCharset(File file, String fromCharsetName) throws Exception {
		if (!Charset.isSupported(fromCharsetName)) {
			throw new UnsupportedCharsetException(fromCharsetName);
		}
		InputStream inputStream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(inputStream, fromCharsetName);
		char[] chs = new char[(int) file.length()];
		reader.read(chs);
		String str = new String(chs).trim();
		reader.close();
		return str;
	}

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid.toUpperCase();
	}

	/**
	 * 以指定编码方式写文本文件，存在会覆盖
	 * 
	 * @param file
	 *            要写入的文件
	 * @param toCharsetName
	 *            要转换的编码
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public static void saveFile2Charset(File file, String toCharsetName, String content) throws Exception {
		if (!Charset.isSupported(toCharsetName)) {
			throw new UnsupportedCharsetException(toCharsetName);
		}
		OutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter outWrite = new OutputStreamWriter(outputStream, toCharsetName);
		outWrite.write(content);
		outWrite.close();
	}

	/**
	 * 获取不带后缀名的文件名
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileNameWithoutSuffix(File file) {
		String file_name = file.getName();
		return file_name.substring(0, file_name.lastIndexOf("."));
	}
	
	public static String getFileNameWithoutSuffix(String filename) {
		return filename.substring(0, filename.lastIndexOf("."));
	}
	
	
	public static String getFilePathWithoutSuffix(File file) {
		String file_path = file.getPath();
		return file_path.substring(0, file_path.lastIndexOf("."));
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param file
	 * @return
	 */
	public static String getSuffix(File file) {
		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		return suffix;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param file
	 * @return
	 */
	public static String getSuffix(String filename) {
		String suffix = filename.substring(filename.lastIndexOf(".") + 1);
		return suffix;
	}
}
