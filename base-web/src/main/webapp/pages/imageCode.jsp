<%--
  Created by IntelliJ IDEA.
  User: reare
  Date: 2019/10/13
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.awt.*" %>

<%
    int width=80;
    int height=32;
    //创建底图
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    //设置背景颜色
    g.setColor(new Color(0xDCDCDC));
    g.fillRect(0,0,width-1,height-1);
    //画出边框
    g.setColor(Color.BLACK);
    g.drawRect(0,0,width,height);
    //创建随机生成器
    Random rdm = new Random();
    String hash1=Integer.toHexString(rdm.nextInt());
    //字样随机变形布局
    for(int i=0;i<50;i++){
        int x = rdm.nextInt(width);
        int y=rdm.nextInt(height);
        g.drawOval(x,y,0,0);
    }
    //生成一个随机码
    String capstr = hash1.substring(0, 4);
    session.setAttribute("key", capstr);
    g.setColor(new Color(0,100,0));
    g.setFont(new Font("Candara", Font.BOLD, 24));
    g.drawString(capstr, 8,24);
    g.dispose();
    response.setContentType("image/jpeg");
    out.clear();
    out=pageContext.pushBody();
    OutputStream strm=response.getOutputStream();
    ImageIO.write(image, "jpeg", strm);
    strm.close();
%>
