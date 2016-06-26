package ch00;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Random;

public class se00 {
    public static void main(String[] args) throws InterruptedException, IOException {
        Random r = new Random();
        for (int i=0; i<1000; i++) {
            System.out.println(i);
            String request = "curl \"http://activity.go.lemall.com/game/extractPrize.json?updata=" +
                    new Date().getTime() +
                    "\" -H \"Cookie: tj2_lc=a593c634f670e561e06b9a91a9d8f127; tj_sg=1; webtrekk_cookie_record=1; pgv_pvi=1467475968; pgv_si=s8519940096; COOKIE_USER_CITY=; COOKIE_THIRDPARTY_ID=50743445; COOKIE_CHECK_TIME=1466303267695; COOKIE_TOKEN_ID_N=bdad70af711416eb00039d14f1a82182; COOKIE_USER_IP=10.130.94.133; COOKIE_USER_NAME=7d0c8aefc46b037c60d0a26a5a9986fd; COOKIE_NICKNAME=Castlebin; COOKIE_USER_LEVEL_ID=1; COOKIE_SESSION_ID=B4BCE4E511789945B7C047FAAF327029; COOKIE_TOKEN_ID=be491302e36ef5f2daceeb6198d056da; COOKIE_TOKEN_DATE=1466303267689; COOKIE_USER_ID=250743445; COOKIE_USER_TYPE=1; COOKIE_LOGIN_TYPE=5; COOKIE_USER_INFO=Castlebin\"%\"5E\"%\"5E15088748735\"%\"5E50743445\"%\"5Ehttp\"%\"3A\"%\"2F\"%\"2Fi1.letvimg.com\"%\"2Fimg\"%\"2F201207\"%\"2F30\"%\"2Ftx298.png\"%\"2Chttp\"%\"3A\"%\"2F\"%\"2Fi0.letvimg.com\"%\"2Fimg\"%\"2F201207\"%\"2F30\"%\"2Ftx200.png\"%\"2Chttp\"%\"3A\"%\"2F\"%\"2Fi0.letvimg.com\"%\"2Fimg\"%\"2F201207\"%\"2F30\"%\"2Ftx70.png\"%\"2Chttp\"%\"3A\"%\"2F\"%\"2Fi3.letvimg.com\"%\"2Fimg\"%\"2F201207\"%\"2F30\"%\"2Ftx50.png\"%\"5E; sourceUrl=http\"%\"3A//www.lemall.com/login.html\"%\"3Ffrom\"%\"3Dhttp\"%\"253A\"%\"252F\"%\"252Fwww.lemall.com\"%\"252Fcn\"%\"252Fsale\"%\"252Fshengtai618.html; __xsptplus104=104.3.1466303210.1466303298.3\"%\"234\"%\"7C\"%\"7C\"%\"7C\"%\"7C\"%\"7C\"%\"23\"%\"23ziCOAWO1CtGZlp7lKy_-yoS5-eIdL2pT\"%\"23; tj_sid=6926e2c05fac2c89d92e712d48f52310; tj_lc=91c7006ee943b3f525504b49f03814f8\" -H \"Origin: http://www.lemall.com\" -H \"Accept-Encoding: gzip, deflate\" -H \"Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4\" -H \"User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36\" -H \"Content-Type: application/x-www-form-urlencoded\" -H \"Accept: */*\" -H \"Referer: http://www.lemall.com/htmlResource/swf/LaoHuJi_20160618_05.swf\" -H \"X-Requested-With: ShockwaveFlash/21.0.0.242\" -H \"Connection: keep-alive\" -H \"DNT: 1\" --data \"acId=1&requestType=0\" --compressed";
            Process p = Runtime.getRuntime().exec(request);
            try(
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()))
                    ) {
                String buff = null;
                while ((buff = br.readLine()) != null) {
                    System.out.println(buff);
                }
            }
            int wait = r.nextInt(20);
            System.out.println("wait: "+wait);
            Thread.sleep(wait * 1000);
        }
    }
}
