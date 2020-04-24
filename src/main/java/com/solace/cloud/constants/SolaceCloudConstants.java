package com.solace.cloud.constants;

import java.util.HashMap;

//TODO: Change everything to Enums or EnumMaps
public final class SolaceCloudConstants {

    public static String[] AWS_REGIONS = { "aws-ap-northeast-1a", "aws-ap-southeast-1a", "aws-ap-southeast-2c",
	    "aws-ca-central-1a", "aws-eu-central-1a", "aws-eu-west-1a", "aws-eu-west-2a", "aws-us-east-1b",
	    "aws-us-east-2a", "aws-us-west-2a" };

    public static String[] AZURE_REGIONS = { "azure-eastus2", "azure-france-central", "azure-southeastasia",
	    "azure-uksouth", "azure-westeurope" };

    public static String[] GCP_REGIONS = { "gcp-asia-southeast1-a", "gcp-europe-west1-b", "gcp-europe-west2-a",
	    "gcp-us-central1-a", "gcp-us-east4-a" };

    public static HashMap<String, String[]> SERVICE_TYPE_ID_BY_REGION = populateServiceTypeIdByRegion();

    public static HashMap<String, String[]> populateServiceTypeIdByRegion() {
	HashMap<String, String[]> tempMap = new HashMap<String, String[]>();

	tempMap.put("aws-ap-northeast-1a", new String[] { "developer", "enterprise-kilo" });
	tempMap.put("aws-ap-southeast-1a", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "free", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("aws-ap-southeast-2c", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "free", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("aws-ca-central-1a", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("aws-eu-central-1a", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("aws-eu-west-1a", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "free", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("aws-eu-west-2a", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "free", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("aws-us-east-1b", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "free", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("aws-us-east-2a", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("aws-us-west-2a", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("azure-eastus2", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("azure-france-central", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("azure-southeastasia", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("azure-uksouth", new String[] { "developer", "enterprise-kilo" });
	tempMap.put("azure-westeurope", new String[] { "enterprise-giga", "enterprise-tera", "developer",
		"enterprise-100k-tera", "enterprise-kilo", "enterprise-mega" });
	tempMap.put("gcp-asia-southeast1-a", new String[] { "developer", "enterprise-kilo" });
	tempMap.put("gcp-europe-west1-b", new String[] { "developer", "enterprise-kilo" });
	tempMap.put("gcp-europe-west2-a", new String[] { "developer", "enterprise-kilo" });
	tempMap.put("gcp-us-central1-a", new String[] { "developer", "enterprise-kilo" });
	tempMap.put("gcp-us-east4-a", new String[] { "developer", "enterprise-kilo" });

	return tempMap;
    }

    public static final String ADMIN_PROGRESS_COMPLETED = "completed";
    public static final String ADMIN_PROGRESS_FAILED = "failed";
    public static final String ADMIN_PROGRESS_INITIAL = "initial";
    public static final String ADMIN_PROGRESS_INPROGRESS = "inProgress";
}
