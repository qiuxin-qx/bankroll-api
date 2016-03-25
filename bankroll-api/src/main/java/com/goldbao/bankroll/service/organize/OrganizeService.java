package com.goldbao.bankroll.service.organize;

import com.goldbao.bankroll.model.Organize;

public interface OrganizeService {

	Organize addOrgianze(Organize organize);

	Organize getOrganizeById(Long id);

	Organize updateOrganize(Organize organize);


}
