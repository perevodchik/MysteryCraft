package perevodchik.capability.quest;

import java.util.ArrayList;

public class QuestContainer implements IQuestContainer {
    private ArrayList<Quest> list = new ArrayList<>();

    @Override
    public Boolean canSelectMore() {
        return this.list.size() < 6;
    }

    @Override
    public ArrayList<Quest> getList() {
        return this.list;
    }

    @Override
    public void setList(ArrayList<Quest> list) {
        this.list = list;
    }

    @Override
    public void addQuest(Quest q) {
        this.list.add(q);
    }

    @Override
    public void removeQuest(Quest q) {
        this.list.remove(q);
    }

    @Override
    public Quest get(int c) {
        return this.list.get(c);
    }

    @Override
    public boolean contains(Quest q) {
        for(Quest qq : this.list) {
            if(qq.getId().equals(q.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
}
