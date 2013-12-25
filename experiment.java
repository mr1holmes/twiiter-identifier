import java.io.*;
import java.util.*;

public class twit4 {
	public static final float BETA=0.1f;
	public static void main(String[] args) throws IOException {
		twit4 ob=new twit4();
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
		float hJustin=1f,hGates=1f,hGoogle=1f,hObama=1f,hKing=1f;
		float mJustin=1f,mGates=1f,mGoogle=1f,mObama=1f,mKing=1f;
		float wJustin=1f,wGates=1f,wGoogle=1f,wObama=1f,wKing=1f;
		float pJustin=1f,pGates=1f,pKing=1f,pGoogle=1f,pObama=1f;	
		String tweet=consoleReader.readLine();
		String token,person=null;
		float max=-0f;
		StringTokenizer st2=new StringTokenizer(tweet);
		while(st2.hasMoreTokens()){
				token=st2.nextToken();
			if(isHashTag(token)){
				if(barackWords.containsKey(token))hObama*=((barackWords.get(token))+BETA)/((barackWords.size())+BETA);
				if(justinWords.containsKey(token))hJustin*=((justinWords.get(token))+BETA)/((justinWords.size())+BETA);
				if(kingWords.containsKey(token))hKing*=((kingWords.get(token))+BETA)/((kingWords.size())+BETA);
				if(googleWords.containsKey(token))hGoogle*=((googleWords.get(token))+BETA)/((googleWords.size())+BETA);
				if(gatesWords.containsKey(token))hGates*=((gatesWords.get(token))+BETA)/((gatesWords.size())+BETA);
			}else if(isMention(token)){
				if(barackWords.containsKey(token))mObama*=((barackWords.get(token))+BETA)/((barackWords.size())+BETA);
				if(justinWords.containsKey(token))mJustin*=((justinWords.get(token))+BETA)/((justinWords.size())+BETA);
				if(kingWords.containsKey(token))mKing*=((kingWords.get(token))+BETA)/((kingWords.size())+BETA);
				if(googleWords.containsKey(token))mGoogle*=((googleWords.get(token))+BETA)/((googleWords.size())+BETA);
				if(gatesWords.containsKey(token))mGates*=((gatesWords.get(token))+BETA)/((gatesWords.size())+BETA);
			}else{
				if(barackWords.containsKey(token))wObama*=((barackWords.get(token))+BETA)/((barackWords.size())+BETA);
				if(justinWords.containsKey(token))wJustin*=((justinWords.get(token))+BETA)/((justinWords.size())+BETA);
				if(kingWords.containsKey(token))wKing*=((kingWords.get(token))+BETA)/((kingWords.size())+BETA);
				if(googleWords.containsKey(token))wGoogle*=((googleWords.get(token))+BETA)/((googleWords.size())+BETA);
				if(gatesWords.containsKey(token))wGates*=((gatesWords.get(token))+BETA)/((gatesWords.size())+BETA);
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

			
