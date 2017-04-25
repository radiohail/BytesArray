import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class BytesZip {
	public static void main(String[] args) throws Exception {
		StringBuilder value = new StringBuilder() ;
		for (int i = 0; i < 100; i++) {
			value.append("00000000111222233334455");
		}
		//字符串压缩为byte数组
		byte[] values = value.toString().getBytes() ;
		System.out.println("解压前大小"+values.length);
		values = compress(values) ;
		System.out.println("解压后大小"+values.length);
		//把压缩后的byte数组转为字符串
		String str = new String(values,"iso8859-1") ;
		
		//传输字符串
		System.out.println(str);
		
		//将接受到的字符串转换为byte数组
		values = str.getBytes("iso8859-1") ;
		//解压缩这个byte数组
		values = decompress(values) ;
		System.out.println(new String(values, "utf-8"));

	}
	
	 /** 
     * 数据解压缩 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decompress(byte[] data) 
			throws Exception {

        ByteArrayInputStream bais = new ByteArrayInputStream(data);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
  
        // 解压缩  
  
        decompress(bais, baos);  
  
        data = baos.toByteArray();  
  
        baos.flush();  
        baos.close();  
  
        bais.close();  
  
        return data;  
    } 
    
    /** 
     * 数据解压缩 
     *  
     * @param is 
     * @param os 
     * @throws Exception 
     */  
    public static void decompress(InputStream is, OutputStream os)  
            throws Exception {  
      
        GZIPInputStream gis = new GZIPInputStream(is);  
      
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = gis.read(data, 0, BUFFER)) != -1) {  
            os.write(data, 0, count);  
        }  
      
        gis.close();  
    }  

	public static byte[] compress(byte[] data)
			throws Exception {
				
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 压缩
		compress(bais, baos);

		byte[] output = baos.toByteArray();

		baos.flush();
		baos.close();

		bais.close();

		return output;
	}

	static final int BUFFER = 10240 ; 

	/**
	 * 数据压缩
	 * 
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	public static void compress(InputStream is, OutputStream os)
			throws Exception {

		GZIPOutputStream gos = new GZIPOutputStream(os);

		int count;
		byte data[] = new byte[BUFFER];
		while ((count = is.read(data, 0, BUFFER)) != -1) {
			gos.write(data, 0, count);
		}

		gos.finish();

		gos.flush();
		gos.close();
	}

}
