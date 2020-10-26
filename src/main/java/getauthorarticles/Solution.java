package getauthorarticles;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

class Book{
    String title;
    String url;
    String author;
    int num_comments;
    String story_id;
    String story_title;
    String story_url;
    String parent_id;
    int created_at;
}

class ResultPayload{
    String page;
    int per_page;
    int total;
    int total_pages;
    List<Book> data;
}

class Result {

    private static ResultPayload loadPage(String author, int pageNum){

        String address = "https://jsonmock.hackerrank.com/api/articles?author=" + author + "&page=" + pageNum;
        ResultPayload resultPayload = null;

        try{
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream inputStream = connection.getInputStream();

            String responseBodyText = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
                    .collect(Collectors.joining("\n"));

            Gson gson = new Gson();
            resultPayload = gson.fromJson(responseBodyText, ResultPayload.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return resultPayload;
    }

    /*
     * Complete the 'getArticleTitles' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING author as parameter.
     *
     * URL for cut and paste:
     * https://jsonmock.hackerrank.com/api/articles?author=<authorName>&page=<num>
     *
     */

    public static List<String> getArticleTitles(String author) {

        int pageNum = 1;
        List<String> results = new ArrayList<>();
        processPage(author, results, pageNum);

        return results;
    }

    private static void processPage(String author, List<String> results, int pageNum) {

        ResultPayload resultPayload = loadPage(author, pageNum);

        extractTitles(results, resultPayload);

        if(pageNum < resultPayload.total_pages){
            processPage(author, results, pageNum + 1);
        }
    }

    private static void extractTitles(List<String> results, ResultPayload resultPayload) {
        for (Book book: resultPayload.data){

            if(book.title != null){
                results.add(book.title);
            }
            else if (book.title == null && book.story_title != null) {
                results.add(book.story_title);
            }
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sourceFilePath = "/input-getauthortitles.txt";
        InputStream inputStream = Solution.class.getResourceAsStream(sourceFilePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String author = bufferedReader.readLine();

        List<String> result = Result.getArticleTitles(author);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}