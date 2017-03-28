package com.hyq.service;

import com.hyq.entity.Bug;
import com.hyq.entity.PageBean;

import java.util.List;
import java.util.Set;

/**
 * Created by genius on 2017/3/13.
 */
public interface BugService {

    public List<Bug> findBugList(Bug s_bug, PageBean pageBean);

    public Bug getBugById(int id);

    public Object saveBug(Bug bug);

    public void deleteBug(int id);

    public int deleteBugs(Set<Integer> ids);

}
