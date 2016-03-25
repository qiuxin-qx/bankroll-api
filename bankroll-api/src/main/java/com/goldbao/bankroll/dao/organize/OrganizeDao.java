package com.goldbao.bankroll.dao.organize;

import com.goldbao.bankroll.model.Organize;

public interface OrganizeDao {
	
	Organize addOrganize(Organize organize);

	Organize getOrganizeById(Long id);

	Organize updateOrganize(Organize organize);
}
