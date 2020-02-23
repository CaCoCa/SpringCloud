package com.vajun.admin.tool;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class CodeGenerator {

    private CodeGeneConfig config;

    public CodeGenerator(CodeGeneConfig config) {
        this.config = mergeProps(config);
    }

    private CodeGeneConfig mergeProps(CodeGeneConfig config) {
        if (null == config) {
            config = new CodeGeneConfig();
        }
        Properties props = new Properties();
        try {
            props.load(CodeGenerator.class.getClassLoader().getResourceAsStream("code.properties"));
        } catch (IOException e) {
            log.error("CodeGenerator读取配置失败", e);
        }
        if (StringUtils.isBlank(config.getGeneModule())) {
            config.setGeneModule(props.getProperty(CodeGeneProps.GENE_MODULE));
        }
        if (StringUtils.isBlank(config.getGenePkgModule())) {
            config.setGenePkgModule(props.getProperty(CodeGeneProps.GENE_PKG_MODULE));
        }
        if (StringUtils.isBlank(config.getGenePkgParent())) {
            config.setGenePkgParent(props.getProperty(CodeGeneProps.GENE_PKG_PARENT));
        }

        if (StringUtils.isBlank(config.getGeneDsUrl())) {
            config.setGeneDsUrl(props.getProperty(CodeGeneProps.GENE_DS_URL));
        }
        if (StringUtils.isBlank(config.getGeneDsUsername())) {
            config.setGeneDsUsername(props.getProperty(CodeGeneProps.GENE_DS_USERNAME));
        }
        if (StringUtils.isBlank(config.getGeneDsPassword())) {
            config.setGeneDsPassword(props.getProperty(CodeGeneProps.GENE_DS_PASSWORD));
        }

        if (StringUtils.isBlank(config.getGeneTableNames())) {
            config.setGeneTableNames(props.getProperty(CodeGeneProps.GENE_TABLE_NAMES));
        }
        if (StringUtils.isBlank(config.getGeneTablePrefix())) {
            config.setGeneTablePrefix(props.getProperty(CodeGeneProps.GENE_TABLE_PREFIX));
        }

        if (StringUtils.isBlank(config.getGeneBaseEntity())) {
            config.setGeneBaseEntity(props.getProperty(CodeGeneProps.GENE_BASE_ENTITY));
        }
        if (StringUtils.isBlank(config.getGeneBaseEntityFields())) {
            config.setGeneBaseEntityFields(props.getProperty(CodeGeneProps.GENE_BASE_ENTITY_FIELDS));
        }
        if (StringUtils.isBlank(config.getGeneBaseController())) {
            config.setGeneBaseController(props.getProperty(CodeGeneProps.GENE_BASE_CONTROLLER));
        }


        if (StringUtils.isBlank(config.getGeneEntityName())) {
            config.setGeneEntityName(props.getProperty(CodeGeneProps.GENE_ENTITY_NAME));
        }
        if (StringUtils.isBlank(config.getGeneAuthor())) {
            config.setGeneAuthor(props.getProperty(CodeGeneProps.GENE_AUTHOR));
        }
        return config;
    }


    public void execute() {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        StringBuilder builder = new StringBuilder(System.getProperty("user.dir"));
        if (StringUtils.isNotBlank(config.getGeneModule())) {
            builder.append("/" + config.getGeneModule());
        }
        builder.append("/src/main/java");
        globalConfig.setEntityName(config.getGeneEntityName());
        globalConfig.setOutputDir(builder.toString());
        globalConfig.setAuthor(config.getGeneAuthor());
        globalConfig.setOpen(false);
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(config.getGeneDsUrl());
        dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername(config.getGeneDsUsername());
        dataSourceConfig.setPassword(config.getGeneDsPassword());
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(config.getGenePkgModule());
        packageConfig.setParent(config.getGenePkgParent());
        generator.setPackageInfo(packageConfig);

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                // 用来作为类注释的时间，模板中通过${cfg.datetime}获取
                map.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return builder +"/com/vajun/admin/mapper/"+tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(focList);
        generator.setCfg(injectionConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        if (StringUtils.isNotBlank(config.getGeneBaseEntity())) {
            strategy.setSuperEntityClass(config.getGeneBaseEntity());
        }
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        if (StringUtils.isNotBlank(config.getGeneBaseController())) {
            strategy.setSuperControllerClass(config.getGeneBaseController());
        }
        strategy.setInclude(config.getGeneTableNames().split(","));
        String superFields = config.getGeneBaseEntityFields();
        if(StringUtils.isNotBlank(superFields)) {
            strategy.setSuperEntityColumns(superFields.split(","));
        }
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(config.getGeneTablePrefix());
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }

    public static void main(String[] args) {
        CodeGeneConfig codeGeneConfig = new CodeGeneConfig();
        new CodeGenerator(codeGeneConfig).execute();
    }
}
