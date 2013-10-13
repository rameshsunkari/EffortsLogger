package com.efforts.service.action;

import java.util.List;

import javax.ejb.Local;

import com.efforts.model.ProjectInfo;

@Local
public interface ProjectInfoServiceBeanLocal {

	public List<ProjectInfo> getProjectInfoList();

	public void addProjectInfo(ProjectInfo project);

	public ProjectInfo getProjectById(Long projectId);

	public void updateProjectInfo(ProjectInfo project);

}
