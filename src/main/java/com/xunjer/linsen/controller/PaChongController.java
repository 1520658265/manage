package com.xunjer.linsen.controller;

import com.xunjer.linsen.common.model.ResultModel;
import com.xunjer.linsen.dao.PaChongDaoImpl;
import com.xunjer.linsen.model.entity.EventInfo;
import com.xunjer.linsen.model.entity.PaChong;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linsen
 * @date 2020/4/1 20:57
 * @tips 明日复明日 明日何其多
 */
@RestController
@RequestMapping("demo")
@Api(value = "demoController",tags = "测试接口")
public class PaChongController {

    @Autowired
    private PaChongDaoImpl paChongDao;

    @RequestMapping(value = "paChong",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "增加事件")
    public ResultModel<Boolean> add(){

        try{
            Document document = null;
            Document child = null;
            for(int type=1;type<=3;type++){
                for(int page=1;page<=110;page++){
                    String p = page==1 ? "" : "pg"+page+"-";
                    String url = "http://category.dangdang.com/"+p+"cp01.41.70.01.0"+type+".00.html";
                    document = Jsoup.connect(url)
                            .header("Accept-Language", "zh-CN,zh;q=0.9")
                            .header("Connection", "keep-alive")
                            .header("Host","category.dangdang.com")
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                            .get();
                    Element img = document.getElementsByClass("bigimg").get(0);
                    Elements li = img.getElementsByTag("li");
                    for(Element temple : li){
                        Element a = temple.getElementsByTag("a").get(0);
                        String childUrl = a.attr("href");
                        child = Jsoup.connect(childUrl)
                                .header("Accept-Language", "zh-CN,zh;q=0.9")
                                .header("Connection", "keep-alive")
                                .header("Host","category.dangdang.com")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                                .get();
                        Element nameE = child.getElementsByTag("h1").get(0);
                        PaChong paChong = new PaChong();
                        paChong.setName(nameE.text());
                        Element detail = child.getElementsByClass("messbox_info").get(0);
                        Elements authorE = detail.getElementsByClass("t1");
                        String author = "",press="";
                        if(authorE!=null && authorE.size()==1){
                            press = authorE.get(0).getElementsByTag("span").get(0).text();
                        }
                        if(authorE!=null && authorE.size()==2){
                            author = authorE.get(0).getElementsByTag("span").get(0).text();
                            press = authorE.get(1).getElementsByTag("span").get(0).text();
                        }
                        if(authorE!=null && authorE.size()==3){
                            author = authorE.get(0).getElementsByTag("span").get(0).text();
                            press = authorE.get(1).getElementsByTag("span").get(0).text();
                        }
                        paChong.setAge(author);
                        paChong.setPress(press);
                        Element scoreCom = detail.getElementsByClass("pinglun clearfix").get(0);
                        Element scoreE = scoreCom.getElementsByClass("star_box").get(0);
                        Element scoreEE = scoreE.getElementsByTag("span").get(0);
                        String scoreStr = scoreEE.attr("style");
                        String score = scoreStr.replace("width:","");
                        score = score.replace("%;","");
                        paChong.setScore(score);
//                        Element seriesName =

                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultModel<>(true);
    }

}
