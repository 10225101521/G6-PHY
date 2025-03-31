package g06.ecnu.heartbridge.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("article_tag")
public class ArticleTag {
    @TableField("article_id")
    private Long articleId;
    @TableField("tag_id")
    private Long tagId;
}
