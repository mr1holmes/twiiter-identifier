import java.io.*;
import java.util.*;

public class twit3 {
	public static void main(String[] args) throws IOException {
		twit3 ob=new twit3();
		ob.go();
	}
	
	public void go() throws IOException{
		BufferedReader consoleReader=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter consoleWriter=new PrintWriter(new OutputStreamWriter(System.out));
		File mf=new File("trainingdata.txt");
		FileReader fr=new FileReader(mf);
		BufferedReader myFileReader=new BufferedReader(fr);
		
		HashMap<String,Integer> barackWords=new HashMap<String,Integer>();
		HashMap<String,Integer> justinWords=new HashMap<String,Integer>();
		HashMap<String,Integer> kingWords=new HashMap<String,Integer>();
		HashMap<String,Integer> gatesWords=new HashMap<String,Integer>();
		HashMap<String,Integer> googleWords=new HashMap<String,Integer>();
		
		
		int noOfBarackTweets=0,noOfjustinTweets=0,noOfKingTweets=0,noOfGatesTweets=0,noOfGoogleTweets=0;
		
		int x=Integer.parseInt(myFileReader.readLine());
		while(x-->0){
			StringTokenizer st=new StringTokenizer(myFileReader.readLine());
			String twitter=st.nextToken();
			String word;
			switch(twitter){
				case "google": 
					while(st.hasMoreTokens()){
						noOfGoogleTweets++;
						word=st.nextToken();
						if(googleWords.containsKey(word))googleWords.put(word,(googleWords.get(word))+1);
						else{googleWords.put(word,1);}
					}
					break;
				case "BarackObama":
					while(st.hasMoreTokens()){
						noOfBarackTweets++;
						word=st.nextToken();
						if(barackWords.containsKey(word))barackWords.put(word,(barackWords.get(word))+1);
						else{barackWords.put(word,1);}
					}
					break;
				case "BillGates":
					while(st.hasMoreTokens()){
						noOfGatesTweets++;
						word=st.nextToken();
						if(gatesWords.containsKey(word))gatesWords.put(word,(gatesWords.get(word))+1);
						else{gatesWords.put(word,1);}
					}
					break;
					
				case "KingJames":
					while(st.hasMoreTokens()){
						noOfKingTweets++;
						word=st.nextToken();
						if(kingWords.containsKey(word))kingWords.put(word,(kingWords.get(word))+1);
						else{kingWords.put(word,1);}
					}
					break;
				case "justinbieber":
					while(st.hasMoreTokens()){
						noOfjustinTweets++;
						word=st.nextToken();
						if(justinWords.containsKey(word))justinWords.put(word,(justinWords.get(word))+1);
						else{justinWords.put(word,1);}
					}
					break;
				}
			}
		int t=Integer.parseInt(consoleReader.readLine());
		while(t-->0){
		long hJustin=0,hGates=0,hGoogle=0,hObama=0,hKing=0;
		long mJustin=0,mGates=0,mGoogle=0,mObama=0,mKing=0;
		long wJustin=0,wGates=0,wGoogle=0,wObama=0,wKing=0;
		long pJustin=0,pGates=0,pKing=0,pGoogle=0,pObama=0;	
		String tweet=consoleReader.readLine();
		String token,person=null;
		long max=0;
		StringTokenizer st2=new StringTokenizer(tweet);
		while(st2.hasMoreTokens()){
				token=st2.nextToken();
			if(isHashTag(token)){
				if(barackWords.containsKey(token))hObama+=100;
				if(justinWords.containsKey(token))hJustin+=100;
				if(kingWords.containsKey(token))hKing+=100;
				if(googleWords.containsKey(token))hGoogle+=100;
				if(gatesWords.containsKey(token))hGates+=100;
			}else if(isMention(token)){
				if(barackWords.containsKey(token))mObama+=100;
				if(justinWords.containsKey(token))mJustin+=100;
				if(kingWords.containsKey(token))mKing+=100;
				if(googleWords.containsKey(token))mGoogle+=100;
				if(gatesWords.containsKey(token))mGates+=100;
			}else{
				if(barackWords.containsKey(token))wObama+=10;
				if(justinWords.containsKey(token))wJustin+=10;
				if(kingWords.containsKey(token))wKing+=10;
				if(googleWords.containsKey(token))wGoogle+=10;
				if(gatesWords.containsKey(token))wGates+=10;
			}
			}
			pObama=hObama+mObama+wObama;
			pJustin=hJustin+mJustin+wJustin;
			pKing=hKing+mKing+wKing;
			pGoogle=hGoogle+mGoogle+wGoogle;
			pGates=hGates+mGates+wGates;
		//consoleWriter.println(pObama+" obama");
		//consoleWriter.println(pJustin+" justin");
		//consoleWriter.println(pKing+" king");
		//consoleWriter.println(pGoogle+" google");
		//consoleWriter.println(pGates+" gates");
			
		if(pObama>max){
			person="BarackObama";
			max=pObama;
		}
		if(pJustin>max){
			person="justinbieber";
			max=pJustin;
		}
		if(pKing>max){
			person="KingJames";
			max=pKing;
		}
		if(pGoogle>max){
			person="google";
			max=pGoogle;
		}
		if(pGates>max){
			person="BillGates";
			max=pGates;
		}
		consoleWriter.println(person);
		
	}
		consoleWriter.close();
		}
		
		boolean isHashTag(String token){
			if(token.charAt(0)=='#')return true;
			else {return false;}
		}
		
		boolean isMention(String token){
			if(token.charAt(0)=='@')return true;
			else {return false;}
		}
	
}

			
