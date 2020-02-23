package com.vajun.admin.tool;

public interface CodeGeneProps {
    /**
     * 项目子模块
     */
    String GENE_MODULE = "gene.module";
    /**
     * 所在包
     */
    String GENE_PKG_MODULE = "gene.pkg.module";
    /**
     * 所在包的父包
     */
    String GENE_PKG_PARENT = "gene.pkg.parent";
    /**
     * 数据源url
     */
    String GENE_DS_URL = "gene.ds.url";
    /**
     * 数据源账号
     */
    String GENE_DS_USERNAME = "gene.ds.username";
    /**
     * 数据源密码
     */
    String GENE_DS_PASSWORD = "gene.ds.password";
    /**
     * 全部表名，多个用逗号分隔
     */
    String GENE_TABLE_NAMES = "gene.table.names";
    /**
     * 表前缀，生成@TableName注解里的表前缀
     */
    String GENE_TABLE_PREFIX = "gene.table.prefix";
    /**
     * entity的父类
     */
    String GENE_BASE_ENTITY = "gene.base.entity";
    /**
     * entity父类的字段，多个用逗号分隔
     */
    String GENE_BASE_ENTITY_FIELDS = "gene.base.entity.fields";
    /**
     * controller的父类
     */
    String GENE_BASE_CONTROLLER = "gene.base.controller";
    /**
     * entity的类名
     */
    String GENE_ENTITY_NAME = "gene.entity.name";

    /**
     * 注释中的author
     */
    String GENE_AUTHOR = "gene.author";
}
