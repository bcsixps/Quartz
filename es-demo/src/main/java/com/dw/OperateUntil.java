package com.dw;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

public abstract class OperateUntil {
	
    private static volatile TransportClient client=null;  
    
    
    /**
     * 双重锁的形式去创建TransportClient的单例
     * @return TransportClient
     */
	@SuppressWarnings("resource")
	public static TransportClient getClient(){
		try {
			if(client==null){ 
				synchronized(OperateUntil.class){
					if(client==null){
						Settings settings = Settings.builder()
						        .put("cluster.name", "es-cluster").build();
						client = new PreBuiltTransportClient(settings)
								.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.88.230"), 9300))  
			                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.88.231"), 9300))
			                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.88.232"), 9300));
					}
				} 
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return client;
	}
	

	public static Result addIndex(String index,String type,String uuid,Object obj) throws JsonProcessingException{
		  if(index == null || index == "" || obj ==null){
			  return null;
		  }
	      TransportClient client = getClient(); 
	      ObjectMapper mapper = new ObjectMapper(); // create once, reuse
	      byte[] json = mapper.writeValueAsBytes(obj);
	      IndexResponse response = client.prepareIndex(index, type,uuid)
	    	        .setSource(json, XContentType.JSON)
	    	        .get();
		  return response.getResult();
	 } 
	

	/**
	 * @param index  索引
	 * @param type   类型
	 * @param uuid   唯一id
	 * @param isSync  是否同步 true:同步  其他:异步
	 * @return
	 */
	public static ActionResponse get(String index, String type, String uuid,boolean isSync){
		TransportClient client =  null;
		try {
			client =  getClient();
			GetResponse response = null;
			if(isSync){  
			   response = client.prepareGet(index, type, uuid).setOperationThreaded(false).get();
			}else{
			   response = client.prepareGet(index, type, uuid).get();
			}
			return response;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static boolean delete(String index, String type, String id){
		TransportClient client =  null;
		boolean flag = false;
		try {
			client =  getClient();
			DeleteResponse response = client.prepareDelete(index, type, id).get();	
			Result result = response.getResult();
			if(Result.DELETED == result){  //删除成功
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * 通过查询条件删除
	 * index:索引
	 * name:字段名
	 * text：匹配内容
	 */
    public static long deleteByQuery(String index,String name, Object text){
    	TransportClient client =  getClient();
    	BulkByScrollResponse response =
    		    DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
    		        .filter(QueryBuilders.matchQuery(name, text)) //只能使用一个 使用多个取最后一个
    		        .source(index)     
    		        .get();       
    	return response.getDeleted();
    }
    
  public static void update(String index,String type, String id){
	TransportClient client =  getClient();
	UpdateRequest updateRequest = new UpdateRequest();
	updateRequest.index(index);
	updateRequest.type(type);
	updateRequest.id(id);
	try {
		updateRequest.doc(jsonBuilder()
		        .startObject()
		        .field("gender", "male")
		    .endObject());
		client.update(updateRequest).get();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	

}
