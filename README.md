Asongo mod-mongo-persistor ODM for vert.x
-----------------------------------------
Still in elaboration

##How to use it (Basic Example)
    Asongo asongo = new Asongo(vertx.eventBus());
    AsongoCollection collection = asongo.getCollection("collection_name");

    collection.findOne("{}").hint("name","age").skip(10).limit(10).as(Model.class).then(new Handler<Model>() {
               		            //Stuff with data
                            });

##Inspiration
this project is inspired from [jongo](http://jongo.org/)

