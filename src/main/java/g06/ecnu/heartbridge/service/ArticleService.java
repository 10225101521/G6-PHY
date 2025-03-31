package g06.ecnu.heartbridge.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import g06.ecnu.heartbridge.entity.Article;
import g06.ecnu.heartbridge.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public ResponseEntity<Map<String, Object>> getArticles(@RequestParam String keyword, @RequestParam Integer page) {
        // 参数校验
        if (page < 1) {
            page = 1; // 页码从1开始
        }

        // 分页参数（每页10条）
        Page<Article> pageParam = new Page<>(page, 10);

        // 构建查询条件
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            queryWrapper.and(wrapper -> wrapper
                    .like("title", keyword)
                    .or()
                    .like("content", keyword)
            );
        }
        queryWrapper.orderByDesc("create_time"); // 按时间倒序

        // 执行分页查询
        Page<Article> articlePage = articleMapper.selectPage(pageParam, queryWrapper);

        // 包装返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("currentPage", articlePage.getCurrent());  // 当前页
        response.put("totalPages", articlePage.getPages());     // 总页数
        response.put("totalItems", articlePage.getTotal());     // 总记录数
        response.put("articles", articlePage.getRecords());    // 数据列表

        return ResponseEntity.ok(response);
    }

    /*
    public ResponseEntity<Map<String, Object>> getArticles(@RequestParam String keyword, @RequestParam Integer page) {
        try{
            //TODO 从数据库查询keyword、page参数对应的数据并返回成JSON
            // 模拟响应数据
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();

            List<Map<String, Object>> articles = new ArrayList<>();

            // 构建第一个文章
            Map<String, Object> article = new HashMap<>();
            article.put("article_id", 79);
            article.put("title", "对待焦虑你需要知道这些");
            article.put("writer_name", "钞桂英");
            article.put("preview", "ex in Duis officia non");
            article.put("views_count", 78);
            article.put("liked_count", 67);
            article.put("create_time", "2026-03-25 00:54:10");
            article.put("tags", Collections.singletonList("焦虑"));

            articles.add(article);

            data.put("total", 2);
            data.put("articles", articles);
            response.put("data", data);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "出错了！");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }*/

    public ResponseEntity<Map<String, Object>> getArticleDetail(@PathVariable("article_id") Long article_id) {
        try {
            Article rt = articleMapper.selectArticleWithTags(article_id);

            // 模拟响应数据
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();

            // 构建文章详情
            data.put("title", rt.getTitle());
            data.put("content", rt.getContent());
            data.put("writer_name", rt.getWriterId());  //TODO 查询作者名称。最好放到Mapper层解决
            data.put("create_time", rt.getCreateTime());
            data.put("views_count", rt.getViewCount());
            data.put("liked_count", rt.getLikedCount());
            data.put("tags", rt.getTags());

            response.put("data", data);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "出错了！");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }



    public ResponseEntity<Map<String, Object>> getSimilarArticles(@PathVariable("article_id") Long article_id) {
        try{
            //TODO 从服务层查询article_id参数对应的数据并返回成JSON

            //1 模拟响应数据
            Map<String, Object> data = new HashMap<>();

            List<Map<String, Object>> articles = new ArrayList<>();

            // 构建相似文章1
            Map<String, Object> article1 = new HashMap<>();
            article1.put("article_id", 54);
            article1.put("title", "如何克服不想上班的心理");
            article1.put("preview", "sit magna consequat qui cupidatat");

            // 构建相似文章2
            Map<String, Object> article2 = new HashMap<>();
            article2.put("article_id", 67);
            article2.put("title", "面对领导pua你该知道这几件事");
            article2.put("preview", "eu mollit magna");

            // 构建相似文章3
            Map<String, Object> article3 = new HashMap<>();
            article3.put("article_id", 95);
            article3.put("title", "职场生存法则");
            article3.put("preview", "et fugiat cillum");

            articles.add(article1);
            articles.add(article2);
            articles.add(article3);

            data.put("articles", articles);

            //2 打包到响应文本里
            Map<String, Object> response = new HashMap<>();
            response.put("data", data);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "出错了！");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

}
