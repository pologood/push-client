package com.easou.novelpush.datasource;



public class DBDatesourceFactory {

    public static final String defaultConfigName = "bookshelves-DB.properties";


//    public DataSource getDBDateSource(String configFile) throws NovelPushServiceException{
//        InputStream in = null;
//        ClassLoader loader = DBDatesourceFactory.class.getClassLoader();
//        if (loader != null) {
//            in = loader.getResourceAsStream(configFile);
//        }
//        if (in == null) {
//            in = ClassLoader.getSystemResourceAsStream(configFile);
//        }
//        if (in == null) {
//            throw new NovelPushServiceException("Could not find resource file: ".concat(configFile));
//        }
//        Properties props = new Properties();
//        try {
//            props.load(in);
//            in.close();
//        } catch (IOException e) {
//            throw new NovelPushServiceException("Could not load resource file: ".concat(configFile),e);
//        }
//        DataSource dataSource = null;
//        try {
//            dataSource = DruidDataSourceFactory.createDataSource(props);
//        } catch (Exception e) {
//            throw new NovelPushServiceException("Could create datasource: ".concat(configFile),e);
//        }
//        return dataSource;
//    }
}
