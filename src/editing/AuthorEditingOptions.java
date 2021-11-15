package editing;

import model.Data;
import model.Record;
import model.Status;

public class AuthorEditingOptions {
    public boolean createNewRecord(String name, String definition){

        if(Data.getDictionary().containsKey(name)) return false;
        else {
            Record record = new Record(name, definition, Status.EDYTOWANE);
            Data.addRecord(record);
            return true;
        }
    }

    public boolean definitionEdit(String name, String definition){
        Record recordToEdit = Data.getDictionary().get(name);

        if(recordToEdit!=null && (recordToEdit.getStatus().equals(Status.EDYTOWANE) ||
                recordToEdit.getStatus().equals(Status.ZRECENZOWANE))){
            Data.getDictionary().get(name).setDefinition(definition);
            return true;
        }
        else return false;
    }

    public boolean passRecord(String name){
        Record recordToPass = Data.getDictionary().get(name);

        if(recordToPass!=null && (recordToPass.getStatus().equals(Status.EDYTOWANE))){
            Data.getDictionary().get(name).setStatus(Status.ZGLOSZONE);
            return true;
        }
        else return false;
    }

}
