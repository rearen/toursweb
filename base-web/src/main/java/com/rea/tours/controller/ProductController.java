package com.rea.tours.controller;


import com.rea.tours.service.IProductService;
import com.rea.tours.utils.myDateEdit;
import com.rea.tours.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    private IProductService productService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new myDateEdit("yyyy-MM-dd HH:mm"));
//        binder.registerCustomEditor(Date.class,new myDateEdit());
    }

    @RequestMapping("/save")
    public String save(Product product)throws Exception{
        productService.save(product);
        return "redirect:findAll";
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception
    {
        System.out.println("表现层：查询所有记录。");
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList", products);
        mv.setViewName("product-list");
        return mv;
    }
}
