import com.rea.tours.service.IProductService;
import com.rea.tours.domain.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestSpring {

    @Test
    public void run1() throws Exception
    {
        // 加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 获取对象
        IProductService ipr = (IProductService) ac.getBean("productServiceImpl");
        // 调用方法
        List<Product> list=ipr.findAll();
        for(Product pr : list){
            System.out.println(pr);
        }
    }

}
