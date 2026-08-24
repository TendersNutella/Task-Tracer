package gson.test.project;

import com.google.gson.annotations.SerializedName;

public enum Status {
    Todo,
    @SerializedName("InProgress")
    In_Progress,
    @SerializedName("NotDone")
    Not_Done,
    Done
}
