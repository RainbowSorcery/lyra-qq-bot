package com.lyra.qqbot;

import com.lyra.qqbot.entity.PonyMusic163;
import com.lyra.qqbot.processor.RandomTrendingPonyImage;
import com.lyra.qqbot.service.IPonyMusic163Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class QqBotApplicationTests {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MusicProcessor musicProcessor;

    @Autowired
    private RandomTrendingPonyImage randomTrendingPonyImage;

    @Test
    public void musicTest() {


    }

    @Test
    void contextLoads() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "_ntes_nuid=9dc727b9fb31876d1c56148495137643; _ntes_nnid=9dc727b9fb31876d1c56148495137643,1652744940210; NMTID=00OH_VcNqJ6Di9Z-kwysJbhiT05_4IAAAGAz0Ztzg; WNMCID=ggrctd.1652744940895.01.0; WEVNSM=1.0.0; WM_NI=jMrtrWd9%2BL2jZtuEXiZbWPR6cx729jqFZX4cNN9JM89WhSVbpu%2Fc3COGF%2FoSr3zQQLm%2BOVZcpQLr3mTNFl8zIEnE5KqrGVnPVmSxh3GsSc3AEH%2Bj9JOoWXxqcOsuzKjYdzU%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed8f761f5b6faa7b766f6b48ab2c44f928a8b82c15ea69ce5abc264f591b6b8ce2af0fea7c3b92aaa93be95f15da8e7e5adb1629aacbba6db639abe99b1d13ea1a8f9d3bc62b39a96b9b7698e92f7abd034fb89a8b4d56ab4abe184b2498d998ad0ee59b2edb6b2d448aff581b7d969b48f8493b26798928a8dbb728b9ba2a6b841b3ae89b7ed5c96b39d99eb3985ad9d84b24b8798e58bbc428db199b9fb7ba191bd96f941aa87ac9bd837e2a3; WM_TID=D1DnhWLWPD1EAUEQEEaFUfD%2FNDlqayTf; playerid=68972777; JSESSIONID-WYYY=ruzEtGhT4rXjYb7QYAa1SIbHTTTZlBYaeMo%2BlFDvHyvA2yxRUiuzqxiw1uP7S9aejIkpH%5CnkzkKhXG%2BrHW%2FvF5AodyMMpxR60IqKZinSUY6O6mpB2I9c%2BPFEiVnuORxtiBRvNnfExplaEeD7A5gPBUXcpRVjGxXXvwjWtBH3omZSbWWH%3A1652746802886; _iuqxldmzr_=33; __remember_me=true; MUSIC_U=550dbca8bd71da0ae3f18f6c956647f6d81e49bbec3a27795f2b69e04ad5eb3d519e07624a9f00530431c28496129f64650d38cd305e197968b5ef83937b2c8a5c52c3a3013d5940a0d2166338885bd7; __csrf=52276988ec07e6229633b14a94566256; ntes_kaola_ad=1");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "params=tq%2FOKIBmh%2BJDx1vYWcvDRH9FnQqd2n4Z%2BynMEjJ86Cccq%2B4PvGTT4oA%2BeldJzFRE1sLfA1zytOZ2nBkwweYkLqoqXWh461MLs8ezv39UR%2FckYCnlTbSdz21XQ6XDVRywdqxllaBnhX0UwXvCJTgIXW6c9PmwD%2B%2F6o3bLH2q7eA5Xm6yBAcJqeX7J0yYRfcT6%2BBxyOI6Kr7dKjirezzeviQjNwq7QTbCyrOXuOO58Tj4%3D&encSecKey=358e14a8fce8575c2dcd8c41edf02461d9aae64a30348fec5876167e8d1e9f0254d8b10c09e8bbe9a1b361ea1b3285c21e402b68f2f07d7649d8e7fd70b634965b7c242d2c9bff8d9e437fd75a7634874aa8bffb3ff1f3521ed5b00ee14758475f2c4d2b78a42e04f8d287a32db0f5486a8c69334d4f1ef5ba29b2d032fc6638";
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Music> jsonObjectResponseEntity = restTemplate.postForEntity("https://music.163.com/weapi/v6/playlist/detail?csrf_token=52276988ec07e6229633b14a94566256",
                stringHttpEntity, Music.class);

        Music body1 = jsonObjectResponseEntity.getBody();

        if (body1 != null) {
            body1.getPrivileges().forEach((e) -> {
                musicProcessor.setId(e.getId());
                Spider.create(musicProcessor)
                        .addUrl("https://music.163.com/song?id=" + e.getId())
                        .thread(threadPoolExecutor, 20)
                        .run();

            });
        }

    }

    public static class Music {
        private Integer code;
        private String relatedVideos;
        private List<privilege> privileges;

        private String urls;
        private String sharedPrivilege;
        private String resEntrance;

        public String getUrls() {
            return urls;
        }

        public void setUrls(String urls) {
            this.urls = urls;
        }

        public String getSharedPrivilege() {
            return sharedPrivilege;
        }

        public void setSharedPrivilege(String sharedPrivilege) {
            this.sharedPrivilege = sharedPrivilege;
        }

        public String getResEntrance() {
            return resEntrance;
        }

        public void setResEntrance(String resEntrance) {
            this.resEntrance = resEntrance;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getRelatedVideos() {
            return relatedVideos;
        }

        public void setRelatedVideos(String relatedVideos) {
            this.relatedVideos = relatedVideos;
        }

        public List<privilege> getPrivileges() {
            return privileges;
        }

        public void setPrivileges(List<privilege> privileges) {
            this.privileges = privileges;
        }

        private static class privilege {
            private Integer id;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }
        }
    }
}

@Component
class MusicProcessor implements PageProcessor {
    @Autowired
    private IPonyMusic163Service ponyMusic163Service;

    private Integer id;
    private final Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000);

    @Override
    public void process(Page page) {

        String s = page.getHtml().xpath("/html/body/div[3]/div[1]/div/div/div[1]/div[1]/div[2]/div[1]/div/em/text()").get();

        PonyMusic163 ponyMusic163 = new PonyMusic163();
        ponyMusic163.setId(id);
        ponyMusic163.setMusicName(s);
        ponyMusic163Service.save(ponyMusic163);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}