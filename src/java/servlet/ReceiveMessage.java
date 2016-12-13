/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.InvalidKeyException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author jirasak_ka
 */
@WebServlet(name = "ReceiveMessage", urlPatterns = {"/ReceiveMessage"})
public class ReceiveMessage extends HttpServlet {

    private static final String CHANNEL_SECRET = "31cc9976980b2005816a49147d8a84c7";
    private static final String CHANNEL_ACCESS_TOKEN = "2SqwVhx4/ZmAqzL+ojMgLnwXf9RHgwCyTCaTiJBgKoPtxE5/O6QXNCJKlkeeyAmGDmdEUeKI51FDQ5mhd/T5iOiRA39zgHcZolgJcOt07PcAwVofZrokUxMcgaMMHPLdm73mYGMLbBNmqxaQTMvRBwdB04t89/1O/w1cDnyilFU=";
    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, java.security.InvalidKeyException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("TEST HEROKU");
        
//        // 署名検証
//        String body = null;
//        try (Stream<String> stream = request.getReader().lines()) {
//            String signature = request.getHeader("X-Line-Signature");
//            body = stream.reduce((s1, s2) -> s1 + "\n" + s2).orElse("");
//            SecretKeySpec key = new SecretKeySpec(CHANNEL_SECRET.getBytes(), HMAC_SHA256);
//            Mac mac = Mac.getInstance(HMAC_SHA256);
//            mac.init(key);
//            byte[] source = body.getBytes(StandardCharsets.UTF_8);
//            String createdSignature = Base64.encodeBase64String(mac.doFinal(source));
//            if (!signature.equals(createdSignature)) {
//                // LINEからのリクエストじゃない場合の処理
//                // 常に200を返す
//                response.setStatus(200);
//                return;
//            }
//        } catch (IOException e) {
//        } catch (NoSuchAlgorithmException e) {
//        } catch (InvalidKeyException e) {
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode events = mapper.readTree(body).path("events");
////        String type = events.path(0).path("message").path("type").asText();
//        // リプライの種類（今回はtext）
////        String type = events.path(0).path("message").path("type").asText(null);
//        String type = events.path(0).path("message").path("type").asText();
//        // テキスト
////        String query = events.path(0).path("message").path("text").asText(null);
//        String query = events.path(0).path("message").path("text").asText();
//        // 返信用Token
////        String replyToken = events.path(0).path("replyToken").asText(null);
//        String replyToken = events.path(0).path("replyToken").asText();
//        // リプライを送る
//        HttpPost httpPost = new HttpPost("https://api.line.me/v2/bot/message/reply");
//        httpPost.setHeader("Content-Type", "application/json");
//        httpPost.setHeader("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN);
//        // 返信用のJSON
//        // サンプルとして「リプライありがとう！」を返す
//        String replyBody = String.format("{\"replyToken\":\"%s\", \"messages\":[{\"type\":\"text\", \"text\":\"リプライありがとう！\"}]}", replyToken);
//        StringEntity params = new StringEntity(replyBody, StandardCharsets.UTF_8);
//        httpPost.setEntity(params);
//        try (CloseableHttpClient client = HttpClients.createDefault();
//                CloseableHttpResponse resp = client.execute(httpPost);
//                BufferedReader br = new BufferedReader(new InputStreamReader(resp.getEntity().getContent(), StandardCharsets.UTF_8))) {
//            int statusCode = resp.getStatusLine().getStatusCode();
//            switch (statusCode) {
//                case 200:
//                    // ↓これは空のJSON({})が返るはず
//                    br.readLine();
//                    break;
//                default:
//            }
//        } catch (final ClientProtocolException e) {
//        } catch (final IOException e) {
//        }
//        response.setStatus(200);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (java.security.InvalidKeyException ex) {
            Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (java.security.InvalidKeyException ex) {
            Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
