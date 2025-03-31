package g06.ecnu.heartbridge.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import g06.ecnu.heartbridge.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章ID查询标签
     */
    @Select("SELECT t.* FROM tag t " +
            "INNER JOIN article_tag at ON t.id = at.tag_id " +
            "WHERE at.article_id = #{articleId}")
    List<Tag> findTagsByArticleId(@Param("articleId") Integer articleId);
}
