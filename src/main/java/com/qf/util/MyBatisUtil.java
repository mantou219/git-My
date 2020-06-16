package com.qf.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtil {
      private static SqlSessionFactory factory;
      private static final ThreadLocal<SqlSession> SQL_SESSION_THREAD_LOCAL=new ThreadLocal<>();
      static {
          try {
              InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
           factory=new SqlSessionFactoryBuilder().build(stream);

          } catch (Exception e) {
              e.printStackTrace();
          }


      }

      public static SqlSession getSqlSession(){
          SqlSession sqlSession = SQL_SESSION_THREAD_LOCAL.get();
          if (null==sqlSession){
              sqlSession=factory.openSession();
              SQL_SESSION_THREAD_LOCAL.set(sqlSession);
          }
        return sqlSession;
      }

    public static void close(){
          getSqlSession().close();
          SQL_SESSION_THREAD_LOCAL.remove();


    }

 public static void commit(){
getSqlSession().commit();
close();

 }


 public static void rollback(){
          getSqlSession().rollback();
          close();
 }
    //直接返回一个
   public static <T> T getMapper(Class<T> t){
         return getSqlSession().getMapper(t);


   }

}
