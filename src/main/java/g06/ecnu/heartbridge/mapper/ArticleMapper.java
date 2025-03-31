package g06.ecnu.heartbridge.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import g06.ecnu.heartbridge.entity.Article;
import g06.ecnu.heartbridge.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    //方式1 提供default实现
    /**
    default List<Article> selectByWriterId(@Param("writerId") Integer writerId){
        return selectList(new QueryWrapper<Article>().eq("writer_id", writerId));
    }*/

    //方式2 用sql注解
    /**
     * 自定义联合查询（使用Mybatis注解实现）
     */
    @Select("SELECT a.*, t.id as tag_id, t.name FROM articles a " +
            "LEFT JOIN article_tag at ON a.id = at.article_id " +
            "LEFT JOIN tag t ON at.tag_id = t.id " +
            "WHERE a.id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "tag_id", property = "tags", many = @Many(select = "com.example.mapper.TagMapper.selectById"))
    })
    Article selectArticleWithTags(@Param("id") Long id);

}
