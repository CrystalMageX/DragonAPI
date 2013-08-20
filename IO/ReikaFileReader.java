/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import Reika.DragonAPI.DragonAPICore;
import Reika.DragonAPI.Libraries.ReikaJavaLibrary;

public class ReikaFileReader extends DragonAPICore {

	public static int getFileLength(File f) {
		int len;
		try {
			LineNumberReader lnr = new LineNumberReader(new FileReader(f));
			lnr.skip(Long.MAX_VALUE);
			len = lnr.getLineNumber()+1+1;
			lnr.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not load file data due to "+e.getCause()+" and "+e.getClass()+" !");
		}
		return len;
	}

	public static String readTextFile(Class root, String path) {
		InputStream in = root.getResourceAsStream(path);
		StringBuilder sb = new StringBuilder();
		BufferedReader p;
		try {
			p = new BufferedReader(new InputStreamReader(in));
		}
		catch (NullPointerException e) {
			ReikaJavaLibrary.pConsole("File "+path+" does not exist!");
			return sb.toString();
		}
		int i = 0;
		try {
			String line = null;
			while((line = p.readLine()) != null) {
				if (!line.isEmpty()) {
					sb.append(line);
					i++;
					sb.append("\n");
				}
			}
			p.close();
		}
		catch (Exception e) {
			ReikaJavaLibrary.pConsole(e.getMessage()+" on loading line "+i);
		}
		return sb.toString();
	}

}
