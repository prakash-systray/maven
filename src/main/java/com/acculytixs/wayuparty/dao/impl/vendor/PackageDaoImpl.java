package com.acculytixs.wayuparty.dao.impl.vendor;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.vendor.PackageDao;
import com.acculytixs.wayuparty.dto.services.PackageMenuItemsDTO;
import com.acculytixs.wayuparty.dto.services.PackageMenuOfferingDTO;
import com.acculytixs.wayuparty.entity.services.PackageMenuOffering;
import com.acculytixs.wayuparty.entity.services.PackageMenuOfferingItems;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

@Repository
@Transactional
public class PackageDaoImpl implements PackageDao{

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<PackageMenuOfferingDTO> getPackageMenuOfferingList(Long vendorId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<PackageMenuOfferingDTO> menuDTOList = new CopyOnWriteArrayList<PackageMenuOfferingDTO>();
		String query = null;
		Query queryObj = null;
		
		
		query = "SELECT " 
				+" menu.id AS menuItemId, "
				+" menu.offering_item AS menuItem, "
				+" menu.uuid AS menuItemUUID "
				
			+" FROM "
			     + "package_menu_offering menu where menu.vendor_id = :vendorId and menu.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("vendorId", vendorId);
		queryObj.setResultTransformer(Transformers.aliasToBean(PackageMenuOfferingDTO.class));
		menuDTOList = queryObj.list();
		
		if(menuDTOList != null && !menuDTOList.isEmpty()) {
			for(PackageMenuOfferingDTO menuDTO : menuDTOList) {
				List<PackageMenuItemsDTO> menuItemsDTOList = null;
				
				query = "SELECT " 
						+" items.id AS itemId, "
						+" items.menu_offering_item AS itemName, "
						+" items.uuid AS itemUUID "
						
					+" FROM "
					     + "package_menu_offering_items items where items.menu_offering_id = :menuId and items.status = '1' ";
			
				
				queryObj = currentSession.createSQLQuery(query);
				queryObj.setBigInteger("menuId", menuDTO.getMenuItemId());
				queryObj.setResultTransformer(Transformers.aliasToBean(PackageMenuItemsDTO.class));
				menuItemsDTOList = queryObj.list();
				menuDTO.setMenuItemsList(menuItemsDTOList);
			}
		}
		return menuDTOList;
	}

	@Override
	public List<PackageMenuOfferingDTO> getMenuOfferingList(Long vendorId) throws Exception {
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<PackageMenuOfferingDTO> menuDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" menu.id AS menuItemId, "
				+" menu.offering_item AS menuItem, "
				+" menu.uuid AS menuItemUUID "
				
			+" FROM "
			     + "package_menu_offering menu where menu.vendor_id = :vendorId and menu.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("vendorId", vendorId);
		queryObj.setResultTransformer(Transformers.aliasToBean(PackageMenuOfferingDTO.class));
		menuDTOList = queryObj.list();
		
		return menuDTOList;
	}

	@Override
	public List<PackageMenuItemsDTO> getPackageMenuItemsList(Long menuId) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		List<PackageMenuItemsDTO> menuItemsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" items.id AS itemId, "
				+" items.menu_offering_item AS itemName, "
				+" items.uuid AS itemUUID "
				
			+" FROM "
			     + "package_menu_offering_items items where items.menu_offering_id = :menuId and items.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("menuId", menuId);
		queryObj.setResultTransformer(Transformers.aliasToBean(PackageMenuItemsDTO.class));
		menuItemsDTOList = queryObj.list();
		
		return menuItemsDTOList;
	}

	@Override
	public Long getMenuOfferingByUUID(String uuid) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String query =  "SELECT "
				+" menu.id "           
				+" FROM package_menu_offering menu "
				+" WHERE menu.uuid = :uuid ";
		
		Query queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("uuid", uuid);
		BigInteger menuId = (BigInteger) queryObj.uniqueResult();
		if(menuId != null) {
			return menuId.longValue();
		}else {
			return null;
		}
		
	}

	@Override
	public PackageMenuOfferingDTO getPackMenuOfferingByUUID(String uuid) throws Exception {
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<PackageMenuOfferingDTO> menuDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" menu.id AS menuItemId, "
				+" menu.offering_item AS menuItem, "
				+" menu.uuid AS menuItemUUID "
				
			+" FROM "
			     + "package_menu_offering menu where menu.uuid = :uuid and menu.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("uuid", uuid);
		queryObj.setResultTransformer(Transformers.aliasToBean(PackageMenuOfferingDTO.class));
		menuDTOList = queryObj.list();
		if(menuDTOList != null && !menuDTOList.isEmpty()) {
			return menuDTOList.get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public boolean isMenuNameExists(String menuItemName, Long vendorId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		boolean flag = true;
		
		String query = "SELECT " 
				+" menu.id "
				+" FROM "
			    +" package_menu_offering menu where menu.offering_item = :menuItemName "
			    +" and menu.vendor_id = :vendorId and menu.status = '1' ";
		
		Query queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("menuItemName", menuItemName);
		queryObj.setLong("vendorId", vendorId);
		BigInteger menuId = (BigInteger) queryObj.uniqueResult();
		if(menuId == null) {
			flag = false;
		}
		return flag;
	}

	@Override
	public void savePackageOfferingItem(PackageMenuOffering packageMenuOffering) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(packageMenuOffering);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public PackageMenuOffering getPackageMenuOfferingByUUID(String uuid) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from PackageMenuOffering menu where menu.uuid = :uuid ";
		List<PackageMenuOffering> packageMenuOfferingList = currentSession.createQuery(query).setString("uuid", uuid).list();
		PackageMenuOffering packageMenuOffering = null;
		if (packageMenuOfferingList != null && packageMenuOfferingList.size() > 0) {
			packageMenuOffering = (PackageMenuOffering) packageMenuOfferingList.iterator().next();
		}
		return packageMenuOffering;
	}

	@Override
	public void savePackageOfferingMenuItems(PackageMenuOfferingItems packageMenuOfferingItems) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(packageMenuOfferingItems);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public PackageMenuOfferingItems getPackageMenuOfferingItemByUUID(String uuid) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from PackageMenuOfferingItems item where item.uuid = :uuid ";
		List<PackageMenuOfferingItems> packageMenuOfferingItemsList = currentSession.createQuery(query).setString("uuid", uuid).list();
		PackageMenuOfferingItems packageMenuOfferingItems = null;
		if (packageMenuOfferingItemsList != null && packageMenuOfferingItemsList.size() > 0) {
			packageMenuOfferingItems = (PackageMenuOfferingItems) packageMenuOfferingItemsList.iterator().next();
		}
		return packageMenuOfferingItems;
	}
}
