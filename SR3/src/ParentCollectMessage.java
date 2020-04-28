package pastry_replica;


import rice.p2p.commonapi.Id;
import rice.p2p.commonapi.Message;
import rice.p2p.commonapi.NodeHandle;
import rice.p2p.scribe.ScribeContent;


public class ParentCollectMessage implements ScribeContent, Message {
 
	Id from;
	
	int key;
	String content;
	
	public ParentCollectMessage(Id id, int key, String content){
		this.from = id;
		this.key = key;
		this.content = content;
		
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
}
