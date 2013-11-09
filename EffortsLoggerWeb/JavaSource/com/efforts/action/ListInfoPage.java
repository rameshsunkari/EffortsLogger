package com.efforts.action;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.efforts.model.EffortsInfo;
import com.efforts.utilities.EffortsConstants;
import com.efforts.utilities.PaginationHelper;

public abstract class ListInfoPage<E> {

	private DataModel items = null;
	private PaginationHelper pagination;
	private int selectedItemIndex;
	private int rowsPerPage = EffortsConstants.ROWS_PER_PAGE;

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
		recreateModel();
		resetPagination();
	}

	public void resetPagination() {
		pagination = null;
	}

	public List<E> findEntireList() {
		return (List<E>) findEntityList(true, -1, -1);
	}

	public List<EffortsInfo> findEntityList(int maxResults, int firstResult) {
		return findEntityList(false, maxResults, firstResult);
	}

	public abstract List<EffortsInfo> findEntityList(boolean all,
			int maxResults, int firstResult);

	public abstract E findEffortsInfo(Long id);

	public int getListCount() {
		return findEntireList().size();
	}

	public DataModel getItems() {
		if (items == null) {
			items = getPagination().createPageDataModel();
		}
		return items;
	}

	public void setItems(DataModel items) {
		this.items = items;
	}

	public PaginationHelper getPagination() {
		if (pagination == null) {
			pagination = new PaginationHelper(rowsPerPage) {
				@Override
				public int getItemsCount() {
					return getListCount();
				}

				@Override
				public DataModel createPageDataModel() {
					return new ListDataModel(findEntityList(getPageSize(),
							getPageFirstItem()));
				}
			};
		}
		return pagination;
	}

	public void setPagination(PaginationHelper pagination) {
		this.pagination = pagination;
	}

	public int getSelectedItemIndex() {
		return selectedItemIndex;
	}

	public void setSelectedItemIndex(int selectedItemIndex) {
		this.selectedItemIndex = selectedItemIndex;
	}

	public String next() {
		getPagination().nextPage();
		recreateModel();
		return "List";
	}

	public String previous() {
		getPagination().previousPage();
		recreateModel();
		return "List";
	}

	private void recreateModel() {
		items = null;
	}

}
