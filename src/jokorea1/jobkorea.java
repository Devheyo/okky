package jokorea1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// 처음에 가져올 때 도큐먼트로 가져오고, 도큐먼트에서 앨리먼츠를 끄집어 내고
// 앨리먼츠를 배열로 해서 엘리먼트를 끄집어내면 된다고 했다.
// 처음에는 URL를 가져와야 겠죠? 도큐먼트로 , HTML을 가져 오는게 도큐먼트 이다.

public class jobkorea {

	// private static String URL = "http://www.jobkorea.co.kr/Search/?";
	private static String URL = "https://okky.kr/articles/event";

	// private static String URL = "https://www.29cm.co.kr/list/best/clicks";
	public static void main(String[] args) throws IOException {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				//	System.out.println("URL :: " + URL + getParameter(KEY_WORD,2));
				String title = "";
				//1. Document를 가져온다.
				//Document doc = Jsoup.connect(URL + getParameter(KEY_WORD,2)).get();
				Document doc = null;
				try {
					doc = Jsoup.connect(URL).get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//2. 목록을 가져온다.
				//System.out.println("" + doc.toString());
			//	Elements elements = doc.select(".lists .lists-cnt .list-default .clear li");
				Elements elements = doc.select("ul.list-group li h5");
				
//				System.out.println("" + elements.toString());
				
				//3.목록(배열)에서 정보를 갖온다.
				int idx = 0;
				
				 /*for(Element element :elements) {
				  
				  System.out.println(++idx+ " : " + element.text());
				  System.out.println("========================================\n\n"); }*/
				 
				
				
				  for(Element element :elements) {
				  
				  System.out.println(++idx+ " : " + element.text());
				  if (idx == 2) {
					  title = element.text();
					  System.out.println(title);
					  break;
				  }
					  
				  System.out.println("========================================\n\n"); }
				 
				 
				
				  try {   
						 String apiURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
						// String key = "";
						// String key1 =Base64.encode(key.getBytes(), 0);
					     URL url = new URL(apiURL);
					     String jsonStr = "template_object={\"object_type\":\"text\",\"text\":\"" + title + "\",\"link\":{\"web_url\":\"https://developers.kakao.com\",\"mobile_web_url\":\"https://developers.kakao.com\"}}";
					     String template_object = URLDecoder.decode(jsonStr, "utf-8");
					     System.out.println(template_object);
					  // JSONParser jsonParser = new JSONParser();
				     //  JSONObject object_type = (JSONObject) jsonParser.parse(jsonStr1);
				         System.out.println(template_object.toString());
				      //   Authorization
				         System.out.println(URL);
				         HttpURLConnection con = (HttpURLConnection)url.openConnection();
				         con.setRequestMethod("POST");
				         con.setRequestProperty("Accept", "application/json");
				         con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
				         con.setRequestProperty("Content-Length", String.valueOf(template_object.length()));
				 	     con.setDoInput(true);
						 con.setDoOutput(true); 

						 con.setRequestProperty( "Authorization","Bearer 7DWhthip8N1ATnuN7-Wns6qD2J5O3QUARRrgjgo9dVwAAAFxIbvYIg");
				         con.setDoOutput(true);
				         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
				         bw.write(template_object.toString());
				         bw.flush();
				         bw.close();
				      //   con.getOutputStream().write(jsonStr1);
				         
				         int responseCode = con.getResponseCode();
				         System.out.println(con.getResponseMessage());
				  //       System.out.println(responseCode);
				         
				     	StringBuilder sb = new StringBuilder();
						if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
							//Stream을 처리해줘야 하는 귀찮음이 있음.
							BufferedReader br = new BufferedReader(
									new InputStreamReader(con.getInputStream(), "utf-8"));
							String line;
							while ((line = br.readLine()) != null) {
								sb.append(line).append("\n");
							}
							br.close();
							System.out.println("" + sb.toString());
						} else {
							System.out.println(con.getResponseMessage());
						}

					
			

			         

			         
				     //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
				//     Map<String,Object> params = new LinkedHashMap<>();
				//     params.put("Authorization", "Bearer caXK9L3ys_l5JgW818LUnfxmgYaB3O_CKnw4GworDR8AAAFw-JSZ5g");

				     // Request Body에 Data를 담기위해 OutputStream 객체를 생성.
				     //OutputStream os = conn.getOutputStream();
				        	 	    
				
				  } catch (Exception e) {
				  
				  e.printStackTrace();
				  
				  }
			}

		};

		// String KEY_WORD = "jquery";

		/*	//	System.out.println("URL :: " + URL + getParameter(KEY_WORD,2));
				String title = "";
				//1. Document를 가져온다.
				//Document doc = Jsoup.connect(URL + getParameter(KEY_WORD,2)).get();
				Document doc = Jsoup.connect(URL).get();
				
				//2. 목록을 가져온다.
				//System.out.println("" + doc.toString());
			//	Elements elements = doc.select(".lists .lists-cnt .list-default .clear li");
				Elements elements = doc.select("ul.list-group li h5");
				
		//		System.out.println("" + elements.toString());
				
				//3.목록(배열)에서 정보를 갖온다.
				int idx = 0;
				
				 for(Element element :elements) {
				  
				  System.out.println(++idx+ " : " + element.text());
				  System.out.println("========================================\n\n"); }
				 
				
				
				  for(Element element :elements) {
				  
				  System.out.println(++idx+ " : " + element.text());
				  if (idx == 2) {
					  title = element.text();
					  System.out.println(title);
					  break;
				  }
					  
				  System.out.println("========================================\n\n"); }
				 
				 
				
				  try {   
						 String apiURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
						// String key = "";
						// String key1 =Base64.encode(key.getBytes(), 0);
					     URL url = new URL(apiURL);
					     String jsonStr = "template_object={\"object_type\":\"text\",\"text\":\"" + title + "\",\"link\":{\"web_url\":\"https://developers.kakao.com\",\"mobile_web_url\":\"https://developers.kakao.com\"}}";
					     String template_object = URLDecoder.decode(jsonStr, "utf-8");
					     System.out.println(template_object);
					  // JSONParser jsonParser = new JSONParser();
				     //  JSONObject object_type = (JSONObject) jsonParser.parse(jsonStr1);
				         System.out.println(template_object.toString());
				      //   Authorization
				         System.out.println(URL);
				         HttpURLConnection con = (HttpURLConnection)url.openConnection();
				         con.setRequestMethod("POST");
				         con.setRequestProperty("Accept", "application/json");
				         con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
				         con.setRequestProperty("Content-Length", String.valueOf(template_object.length()));
				 	     con.setDoInput(true);
						 con.setDoOutput(true); 
		
						 con.setRequestProperty( "Authorization","Bearer 7DWhthip8N1ATnuN7-Wns6qD2J5O3QUARRrgjgo9dVwAAAFxIbvYIg");
				         con.setDoOutput(true);
				         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
				         bw.write(template_object.toString());
				         bw.flush();
				         bw.close();
				      //   con.getOutputStream().write(jsonStr1);
				         
				         int responseCode = con.getResponseCode();
				         System.out.println(con.getResponseMessage());
				  //       System.out.println(responseCode);
				         
				     	StringBuilder sb = new StringBuilder();
						if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
							//Stream을 처리해줘야 하는 귀찮음이 있음.
							BufferedReader br = new BufferedReader(
									new InputStreamReader(con.getInputStream(), "utf-8"));
							String line;
							while ((line = br.readLine()) != null) {
								sb.append(line).append("\n");
							}
							br.close();
							System.out.println("" + sb.toString());
						} else {
							System.out.println(con.getResponseMessage());
						}
		
					
			
		
		     
		
		     
				     //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
				//     Map<String,Object> params = new LinkedHashMap<>();
				//     params.put("Authorization", "Bearer caXK9L3ys_l5JgW818LUnfxmgYaB3O_CKnw4GworDR8AAAFw-JSZ5g");
		
				     // Request Body에 Data를 담기위해 OutputStream 객체를 생성.
				     //OutputStream os = conn.getOutputStream();
				        	 	    
				
				  } catch (Exception e) {
				  
				  e.printStackTrace();
				  
				  }*/

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 1000 * 60 * 60 * 24, TimeUnit.MILLISECONDS);

	}

	/**
	 * URL 완성
	 * 
	 * @param KEY_WORD
	 * @param PAGE
	 * @return
	 */
	public static String getParameter(String KEY_WORD, int PAGE) {
		String params = "stext=" + KEY_WORD + "" + "&IsCoInfoS=false" + "&IsRecruit=false" + "&ord=1" + "&Order=1" + "&page=" + PAGE + "" + "&pageSize=10" + "&pageType=HRP";

		return params;
	}

}
