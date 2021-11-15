package editing;

import model.Data;
import model.Record;
import model.Status;

public class EditorEditingOptions {
    public boolean recordConsideration(String name, String decision){

        Record recordToConsideration = Data.getDictionary().get(name);

        if(recordToConsideration!=null && (recordToConsideration.getStatus().equals(Status.ZGLOSZONE))){

            if(decision.equals("y") || decision.equals("Y")){
                Data.getDictionary().get(name).setStatus(Status.PRZEKAZANE);
                return true;
            }
            else if(decision.equals("n") || decision.equals("N")){
                Data.getDictionary().get(name).setStatus(Status.GOTOWE);
                return true;
            }
            else return false;
        }
        else return false;

    }

    public boolean finalDecision(String name, String decision){

        Record recordToFinalDecision = Data.getDictionary().get(name);

        if(recordToFinalDecision!=null && (recordToFinalDecision.getStatus().equals(Status.GOTOWE) ||
                recordToFinalDecision.getStatus().equals(Status.ZRECENZOWANE))){

            if(decision.equals("y") || decision.equals("Y")){
                Data.getDictionary().get(name).setStatus(Status.ZAAKCEPTOWANE);
                Data.getDictionary().get(name).setReview(null);
                return true;
            }
            else if(decision.equals("n") || decision.equals("N")){
                Data.getDictionary().get(name).setStatus(Status.ODRZUCONE);
                return true;
            }
            else return false;
        }
        else return false;
    }


}
