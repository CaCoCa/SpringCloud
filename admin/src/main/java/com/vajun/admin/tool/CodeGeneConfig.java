package com.vajun.admin.tool;

import lombok.Data;

@Data
public class  CodeGeneConfig{
    private String geneModule;
    private String genePkgModule;
    private String genePkgParent;


    private String geneDsUrl;
    private String geneDsUsername;
    private String geneDsPassword;

    private String geneTableNames;
    private String geneTablePrefix;

    private String geneBaseEntity;
    private String geneBaseEntityFields;
    private String geneBaseController;

    private String geneEntityName;
    private String geneAuthor;
}
