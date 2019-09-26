package com.ubaid.scrape.SOUQScrapper.entity;

import com.fasterxml.jackson.databind.JsonNode;

public class EnArNodes
{
	private JsonNode enNode;
	private JsonNode arNode;
	public JsonNode getEnNode() {
		return enNode;
	}
	public void setEnNode(JsonNode enNode) {
		this.enNode = enNode;
	}
	public JsonNode getArNode() {
		return arNode;
	}
	public void setArNode(JsonNode arNode) {
		this.arNode = arNode;
	}
	public EnArNodes(JsonNode enNode, JsonNode arNode) {
		super();
		this.enNode = enNode;
		this.arNode = arNode;
	}
	public EnArNodes() {
		super();
	}
	
	
	
}
