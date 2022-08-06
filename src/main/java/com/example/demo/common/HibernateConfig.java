package com.example.demo.common;

//@org.springframework.context.annotation.Configuration
//@Component
//@EnableTransactionManagement
//public class HibernateConfig {
//    @Bean
//    public SessionFactory sessionFactory() {
//        SessionFactory sessionFactory = null;
//        
//        try {
//            //registryの作成
//            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//            //MetadataSourcesの作成
//            MetadataSources sources = new MetadataSources(registry);
//            //Metadataの作成
//            Metadata metadata = sources.getMetadataBuilder().build();
//            //SessionFactoryの作成
//            sessionFactory = metadata.getSessionFactoryBuilder().build();
//            
//            //ログの出力
//            if (sessionFactory != null) {
//                System.out.println("セッションファクトリーを取得できました！");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        
//        return sessionFactory;
//    }
// }