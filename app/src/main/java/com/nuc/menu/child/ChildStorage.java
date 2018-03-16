package com.nuc.menu.child;

import java.util.Set;

public interface ChildStorage {
    Set<Child> getChildren();

    void saveChildren(Set<Child> children);
}
