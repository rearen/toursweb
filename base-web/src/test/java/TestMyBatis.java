
import com.rea.tours.dao.IPermissionDao;
import com.rea.tours.dao.IProductDao;
import com.rea.tours.dao.IUserDao;
import com.rea.tours.domain.Permission;
import com.rea.tours.domain.Product;
import com.rea.tours.domain.Role;
import com.rea.tours.domain.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;

public class TestMyBatis {

    /**
     * 测试查询
     * @throws Exception
     */

    @Test
    public void run1() throws Exception {
        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
        IUserDao dao = session.getMapper(IUserDao.class);
        //查询所有数据
        UserInfo u=dao.findByUsername("tom");
        System.out.println(u.getRoles());

//        List<Permission> permissions = dao.findAll();
        for(Role r : u.getRoles()){
            System.out.println(r.getRoleName());
        }
        // 关闭资源
        session.close();
        in.close();
    }

}
