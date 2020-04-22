package pastry_replica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Merge File Example
 *
 * @author Krishna
 *
 */
public class FileMerge  {
	
	
	static String filePath;
	public static int numSpl;
	public static String filePath1;
	
	public FileMerge(String filepath, int numSpl) throws IOException {
        FileMerge.filePath = filepath;
        FileMerge.numSpl = numSpl;
	    }
	
	
	
	public void fileMerger() throws IOException {
		String FILE_NAME = FileMerge.filePath + File.pathSeparator + "original_data.txt";
		File ofile = new File(FILE_NAME);
		FileOutputStream fos;
		FileInputStream fis;
		byte[] fileBytes;
		int bytesRead = 0;
		List<File> list = new ArrayList<File>();
		//list.add(new File(FILE_NAME+".part0"));
		//list.add(new File(FILE_NAME+".part1"));
		//list.add(new File(FILE_NAME+".part2"));
		//list.add(new File(FILE_NAME+".part3"));
		//list.add(new File(FILE_NAME+".part4"));
		//list.add(new File(FILE_NAME+".part5"));
		//list.add(new File(FILE_NAME+".part6"));
		//list.add(new File(FILE_NAME+".part7"));
		
		File directory = new File(FileMerge.filePath);
		File[] listOfFiles = directory.listFiles();
		//int[] indexPart = new int[20];
		//int numPart = 0;
		// Creating an empty TreeMap for parts 
        TreeMap<Integer, String> part_map 
            = new TreeMap<Integer, String>(); 
        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
		if ( listOfFiles.length == (FileMerge.numSpl+1) ) {

		  for (File file : listOfFiles) { 
			  String fileName = file.getName();
			  String[] parts = fileName.split("_");
			  
			  
			  if(parts.length > 1) { // means it has the "part" info, need to be merged
				  int firstPart = Integer.parseInt(parts[1])/10;// get the xx value of partxx
				  
				  if (part_map.get(firstPart) == null) {
					  part_map.put(firstPart, fileName);
					  System.out.println("I find the " + firstPart + " part!");
				  }
				  //if (indexPart[numPart] != firstPart || firstPart == 0 ) {// note that this part has not been merged
					  //if (firstPart != 0 )
						  //System.out.println("Oops, it doesnot start with first part, it starts with " + firstPart + " part!");
					  //indexPart[numPart] = firstPart;
					  //System.out.println("I am now merging the " + firstPart + " part!");
					  //list.add(new File(filePath+"\\replica_part_"+parts[2]));
				  //}
			  }
		  }
			//after sorting all replicas, now save the info that can be merged
		  
			for (Entry<Integer, String> m:part_map.entrySet()) {
				System.out.println("we have the part " + m.getValue());
				list.add(new File(filePath + File.pathSeparator + m.getValue()));
			} 
		}

			  
		try {
		    fos = new FileOutputStream(ofile,true);
		    for (File file : list) {
		        fis = new FileInputStream(file);
		        fileBytes = new byte[(int) file.length()];
		        bytesRead = fis.read(fileBytes, 0,(int)  file.length());
		        assert(bytesRead == fileBytes.length);
		        assert(bytesRead == (int) file.length());
		        fos.write(fileBytes);
		        fos.flush();
		        fileBytes = null;
		        fis.close();
		        fis = null;
		    }
		    fos.close();
		    fos = null;
		}catch (Exception exception){
			exception.printStackTrace();
		}
	}
}
