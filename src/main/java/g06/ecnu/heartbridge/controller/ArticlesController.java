package g06.ecnu.heartbridge.controller;

import g06.ecnu.heartbridge.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/* 以下源代码参考了deepseek的回答：

1、
用Spring创建一个Controller，类名为g06.ecnu.heartbridge.controller.ArticleController,实现以下端口：
GET：/api/articles?keyword=焦虑&page=3
返回JSON类似：
{
  "data": {
    "total": 2,
    "articles": [
      {
        "article_id": 79,
        "title": "对待焦虑你需要知道这些",
        "writer_name": "钞桂英",
        "preview": "ex in Duis officia non",
        "views_count": 78,
        "liked_count": 67,
        "create_time": "2026-03-25 00:54:10",
        "tags": [
          "焦虑"
        ]
      },
      {
        "article_id": 62,
        "title": "被生活击倒了",
        "writer_name": "潮悦",
        "preview": "laboris ex",
        "views_count": 46,
        "liked_count": 54,
        "create_time": "2025-10-10 07:12:12",
        "tags": [
          "焦虑",
          "生活"
        ]
      }
    ]
  }
}

2、继续在上述类中实现以下端口：
GET：/api/articles/{article_id}
返回JSON类似：
{
  "data": {
    "title": "开学恐惧症的背后其实是",
    "content": "voluptate cupidatat deserunt culpac aaaaa",
    "writer_name": "镜榕融",
    "create_time": "2025-11-18 18:18:04",
    "views_count": 30,
    "liked_count": 45,
    "tags": [
      "焦虑",
      "学校生活",
      "人际交往"
    ]
  }
}

3、继续在上述类中实现以下端口：
GET：/api/articles/{article_id}/similar
返回JSON类似：
{
  "data": {
    "articles": [
      {
        "article_id": 54,
        "title": "如何克服不想上班的心理",
        "preview": "sit magna consequat qui cupidatat"
      },
      {
        "article_id": 67,
        "title": "面对领导pua你该知道这几件事",
        "preview": "eu mollit magna"
      },
      {
        "article_id": 95,
        "title": "职场生存法则",
        "preview": "et fugiat cillum"
      }
    ]
  }
}
* */

@Controller
@RequestMapping("/api")
public class ArticlesController {
    @Autowired
    private ArticleService as;

    @GetMapping("/articles")
    public ResponseEntity<Map<String, Object>> a1(@RequestParam String keyword,@RequestParam Integer page) {
        return as.getArticles(keyword,page);
    }

    @GetMapping("/articles/{article_id}")
    public ResponseEntity<Map<String, Object>> a2(@PathVariable("article_id") Long article_id) {
        return as.getArticleDetail(article_id);
    }


    @GetMapping("/articles/{article_id}/similar")
    public ResponseEntity<Map<String, Object>> a3(@PathVariable("article_id") Long article_id) {
        return as.getSimilarArticles(article_id);
    }

}
