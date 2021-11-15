package editing;

import model.Data;
import model.Record;
import model.Status;

public class ReviewerEditingOptions {

    public boolean createReview(String name, String review){

        Record recordToReview = Data.getDictionary().get(name);

        if(recordToReview!=null && (recordToReview.getStatus().equals(Status.PRZEKAZANE))){
            Data.getDictionary().get(name).setReview(review);
            return true;
        }
        else return false;
    }

    public boolean passRecord(String name){

        Record recordToPass = Data.getDictionary().get(name);

        if(recordToPass!=null && (recordToPass.getStatus().equals(Status.PRZEKAZANE))){
            Data.getDictionary().get(name).setStatus(Status.ZRECENZOWANE);
            return true;
        }
        else return false;

    }

}
