package com.zenika.vertx.lib.asongo.operation;

import com.zenika.vertx.lib.asongo.AsongoConfiguration;
import com.zenika.vertx.lib.asongo.then.BasicThenTemplate;
import org.vertx.java.core.json.JsonObject;

import javax.naming.OperationNotSupportedException;

/**
 *
 * @author M. Labusquière
 */
public class MapReduce extends BasicThenTemplate<JsonObject> implements Reduce {
	private final static MongoOperator OPERATOR = MongoOperator.MAP_REDUCE;
	private final AsongoConfiguration configuration;
	private final String collection;
	private final String jsMap;

	private String jsReduce;
	private String out;
	private JsonObject query;
	private JsonObject sort;
	private int limit;
	private String finalize;
	private JsonObject scope;
	private boolean jsMode;
	private boolean verbose;

	public MapReduce(AsongoConfiguration configuration,String collection, String jsMap) {
		this.configuration = configuration;
		this.jsMap = jsMap;
		this.collection = collection;
	}

	@Override
	public MapReduce reduce(String reduce)	{
		this.jsReduce = reduce;
		return this;
	}

	public MapReduce out(String out)	{
		this.out = out;
		return this;
	}

	public MapReduce query(JsonObject query)	{
		this.query = query;
		return this;
	}

	public MapReduce query(String query)	{
		return query(new JsonObject(query));
	}

	public MapReduce sort(JsonObject sort)	{
		this.sort = sort;
		return this;
	}

	public MapReduce sort(String sort)	{
		return sort(new JsonObject(sort));
	}

	public MapReduce limit(int limit)	{
		this.limit = limit;
		return this;
	}

	public MapReduce jsFinalize(String finalize)	{
		this.finalize = finalize;
		return this;
	}

	public MapReduce scope(String scope)	{
		return scope(new JsonObject(scope));
	}

	private MapReduce scope(JsonObject scope) {
		this.scope = scope;
		return this;
	}

	private MapReduce jsMode(boolean jsMode)	{
		this.jsMode = jsMode;
		return this;
	}

	private MapReduce verbose(boolean verbose)	{
		this.verbose = verbose;
		return this;
	}

	@Override
	protected JsonObject getCommand() {
		throw new RuntimeException(new OperationNotSupportedException("Beta version"));
		//TODO set if ectect
		//Refactoring with Command
		/*JsonObject command = new JsonObject();
		JsonObject mapReduce = new JsonObject();

		command.putString("action", OPERATOR.fieldName());
		mapReduce.putString("mapReduce", collection);
		mapReduce.putString("jsMap", jsMap);
		mapReduce.putString("jsReduce", jsReduce);
		mapReduce.putString("out", "collectionmapreduce"); //TODO change and can be as string or an  object
*//*		mapReduce.putObject("query", query);
		mapReduce.putObject("sort", sort);
		mapReduce.putObject("scope", scope);
		mapReduce.putNumber("limit", limit);
		mapReduce.putBoolean("verbose", verbose);
		mapReduce.putBoolean("jsMode", jsMode);
		*//*
		//command.putString("command",mapReduce.toString());
		//command.putString({"action":"command\","command":"{\"mapReduce\":\"asongotest\",\"jsMap\":\"function() {emit(this.cust_id, this.price);};\",\"jsReduce\":\"function(keyCustId, valuesPrices) {return Array.sum(valuesPrices);};\",\"out\":\"collectionmapreduce\"}"});
		System.out.println(mapReduce.toString());
		String mapRedcudeString = "{\"mapReduce\":\"asongotest\",\"jsMap\":\"function() {emit(this.cust_id, thisrice);};\",\"jsReduce\":\"function(keyCustId, valuesPrices) {return Array.sum(valuesPrices);};\",\"out\":\"collectionmapreduce\"}";
		command.putString("command",mapRedcudeString);
		//return command;*/
	}

	@Override
	protected AsongoConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	protected MongoOperator getOperator() {
		return OPERATOR;
	}
}
