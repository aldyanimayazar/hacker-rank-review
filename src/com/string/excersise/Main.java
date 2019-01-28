package com.string.excersise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub
		//String sentence = "Write code for a great times";
		//String mainSentences = longestEvenWord(sentence);
		String substr = "";
		int pageNumber = 4;
		ArrayList<String> titleMovies = getMovieTitle(substr,pageNumber);
		for (String title : titleMovies) {
			System.out.println("Movie Title : "+ title);
		}
		
	}
	// Get movieTitle in Arraylist
	private static ArrayList<String> getMovieTitle(String substr, int pageNumber)
	{
		ArrayList<String> movieList = new ArrayList<String>();
		String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page="+ pageNumber;
		try {
			String tap = getHTML(url);
			JSONObject object = new JSONObject(tap);
			JSONArray posters = object.getJSONArray("data");
			for (int i = 0; i < posters.length(); i++) {
				JSONObject objPoster = posters.getJSONObject(i);
				movieList.add(objPoster.getString("Title"));
			}
			// Print the Movie list on ASCNEDING 
//			for (String movieTitle : movieList) {
//				System.out.println(movieTitle);
//			}
//			System.out.println(holidays);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;
	}
	
	// Get LongesEVen word Function
	private static String longestEvenWord(String sentence)
	{
		String fullSentences = sentence;
		String mainSentences = null;
		int maxValue = 0;
		
		String longesWord = null;
		String[] array = fullSentences.split(" ");
		for (String s : array) {
			if(s.length() > maxValue && (s.length() %2) == 0 ) {
				maxValue = s.length();
				mainSentences = s;
			}
		}
		// 
		if(mainSentences != null) {
			return mainSentences;
		} else {
			return "00";
		}
		
		
	}
	
	// GetUrl http json Function
	 private static String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	   }

}
