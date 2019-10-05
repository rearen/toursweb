
import com.rea.tours.dao.IProductDao;
import com.rea.tours.domain.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

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
        IProductDao dao = session.getMapper(IProductDao.class);
        // 查询所有数据
//        dao.findAll();
        List<Product> list = dao.findAll();
        for(Product pr : list){
            System.out.println(pr);
        }
        // 关闭资源
        session.close();
        in.close();
    }

}
