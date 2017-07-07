import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static xin.utong.core.ProjectConstant.BASE_PACKAGE;

/**
 * mybatis plus 代码生成
 * Created by apple on 2017/7/7.
 */
public class CodeGeneratorPlus {
    //JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/guns?characterEncoding=utf8&useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "1234";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String JAVA_PATH = "/src/main/java"; //java文件路径;
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径
    private static final String AUTHOR = "apple";//@author
    private static final String CONTROLLER_DIR_NAME = "controller";//默认web

    public static void main(String[] args) {
        genCode(new String[]{"user"});
    }

    public static void genCode(String[] tableNames) {
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(PROJECT_PATH + JAVA_PATH) //输出目录
                        .setFileOverride(true) //是否覆盖文件
//                        .setActiveRecord(true) //开始 activeRecord 模式
                        .setEnableCache(false) // XML 二级缓存
                        .setBaseResultMap(true) // XML ResultMap
                        .setBaseColumnList(true) // XML columnList
                        .setAuthor(AUTHOR)
        ).setDataSource(
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL)
                        .setTypeConvert(new MySqlTypeConvert())
                        .setDriverName(JDBC_DIVER_CLASS_NAME)
                        .setUsername(JDBC_USERNAME)
                        .setPassword(JDBC_PASSWORD)
                        .setUrl(JDBC_URL)
        ).setStrategy(
                new StrategyConfig()
//                .setCapitalMode(true) //全局大写命名
//                .setDbColumnUnderline(true) //全局下划线命名
                        .setTablePrefix(new String[]{})
                        .setNaming(NamingStrategy.underline_to_camel) //表名生成策略
                        .setInclude(tableNames) //需要生成的表
//                        .setExclude(new String[]{"role"}) //需要排除的表
//                .setSuperEntityClass("com.*") //自定义实体父类
//                        .setSuperEntityColumns(new String[]{"test_id"})
                        .setTableFillList(tableFillList)
                        // 自定义 mapper 父类
                        // .setSuperMapperClass("com.baomidou.demo.TestMapper")
                        // 自定义 service 父类
                        // .setSuperServiceClass("com.baomidou.demo.TestService")
                        // 自定义 service 实现类父类
                        // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                        // 自定义 controller 父类
                        // .setSuperControllerClass("com.baomidou.demo.TestController")
                        // 【实体】是否生成字段常量（默认 false）
                        // public static final String ID = "test_id";
                        // .setEntityColumnConstant(true)
                        // 【实体】是否为构建者模型（默认 false）
                        // public User setName(String name) {this.name = name; return this;}
                        .setEntityBuilderModel(true)
                        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                        // .setEntityLombokModel(true)
                        // Boolean类型字段是否移除is前缀处理
                        .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
        ).setPackageInfo(
                new PackageConfig()
                        .setParent(BASE_PACKAGE)
                        .setController(CONTROLLER_DIR_NAME)
        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return PROJECT_PATH + RESOURCES_PATH + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                    }
                }))
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig()
                        .setXml(null)
                        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                        // .setController("...");
                        .setEntity("../test-classes/generator/template/mybatis-plus/entity.java.vm")
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );
        mpg.execute();
    }
}
