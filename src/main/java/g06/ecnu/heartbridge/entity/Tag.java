package g06.ecnu.heartbridge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tag")  // 指定关联数据库表名
public class Tag {
    @TableId(type = IdType.AUTO)  // 自增主键
    private Integer id;
    private String name;
}
