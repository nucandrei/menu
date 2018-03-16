package com.nuc.menu.child;

import java.util.Set;

public class ChildManager {
    private final Set<Child> children;
    private ChildStorage childStorage;

    public ChildManager(ChildStorage childStorage) {
        this.childStorage = childStorage;
        this.children = childStorage.getChildren();
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void addChild(Child child) {
        children.add(child);
        childStorage.saveChildren(children);
    }

    public void removeChild(Child child) {
        children.remove(child);
        childStorage.saveChildren(children);
    }

    public boolean nameAlreadyUsed(String text) {
        return children.stream().anyMatch(child -> child.getName().equals(text));
    }
}
