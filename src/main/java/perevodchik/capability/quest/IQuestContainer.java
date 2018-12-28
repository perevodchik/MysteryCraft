package perevodchik.capability.quest;

import java.util.ArrayList;

public interface IQuestContainer {
    Boolean canSelectMore();

    ArrayList<Quest> getList();
    void setList(ArrayList<Quest> list);

    void addQuest(Quest q);
    void removeQuest(Quest q);

    Quest get(int c);

    boolean contains(Quest q);

    int size();

    boolean isEmpty();
}
