package g06.ecnu.heartbridge.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("articles")  // 指定关联数据库表名
public class Article {
    @TableId(type = IdType.AUTO)  // 自增主键
    private Integer id;

    private String title;
    private String content;

    @TableField("writer_id")
    private Integer writerId;  // 字段名与数据库列名映射

    @TableField(value = "create_time", fill = FieldFill.INSERT)  // 插入时自动填充时间
    private LocalDateTime createTime;

    @TableField("view_count")
    private Integer viewCount;

    @TableField("liked_count")
    private Integer likedCount;

    @TableField(exist = false)
    private List<Tag> tags;
}