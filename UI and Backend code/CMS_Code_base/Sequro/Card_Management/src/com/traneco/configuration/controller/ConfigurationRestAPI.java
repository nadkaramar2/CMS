package com.traneco.configuration.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.traneco.clientSearch.model.DocumentMaintainanceRequest;
import com.traneco.clientSearch.model.DocumentMaintainanceResponse;
import com.traneco.common.KeyValueBean;
import com.traneco.common.ResponseBean;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.model.AccountNumberModel;
import com.traneco.configuration.model.AccountTypeModel;
import com.traneco.configuration.model.AddressTypeModel;
import com.traneco.configuration.model.BinModel;
import com.traneco.configuration.model.BranchCodeModel;
import com.traneco.configuration.model.BranchTypeModel;
import com.traneco.configuration.model.CardNoModel;
import com.traneco.configuration.model.CardPlasticModel;
import com.traneco.configuration.model.CardTemplateModel;
import com.traneco.configuration.model.CardTokenModel;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.model.CityModel;
import com.traneco.configuration.model.EmailTypeModel;
import com.traneco.configuration.model.KeyConfigModel;
import com.traneco.configuration.model.MobileTokenModel;
import com.traneco.configuration.model.NcmcServiceModel;
import com.traneco.configuration.model.ProcessResponse;
import com.traneco.configuration.model.StateModel;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.role.model.GroupBean;
import com.traneco.role.services.RoleService;
import com.traneco.service.model.AccountRequest;
import com.traneco.service.services.ClientService;

@RestController
public class ConfigurationRestAPI {

	@Autowired
	LoggerUtil loggerUtil;

	@Autowired
	ConfigurationService configurationService;

	@Autowired
	Environment env;

	@Autowired
	ClientService clientService;

	@Autowired
	RoleService roleService;

	private final String className = ConfigurationRestAPI.class.getName();

	@ResponseBody
	@RequestMapping(value = "/getSearchResult", method = RequestMethod.POST)
	public ResponseBean getSearchResultViaAjax(@RequestBody ResponseBean responseBean) {
		ResponseBean result = new ResponseBean();
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getCardNo", method = RequestMethod.POST)
	public CardNoModel getCardNo(@RequestBody CardNoModel cardNoModel) {
		try {
			String salt = env.getProperty("imps.user.login.salt.password");
			String encPass = Utils.generateSecurePassword(cardNoModel.getPassword(), salt);
			cardNoModel.setPassword(encPass);
			int count = configurationService.validateSenPwd(cardNoModel);
			if (count > 0) 
			{
				Map<String, Object> map = configurationService.getCardNo(cardNoModel.getTokenCard());
				if (map != null && !map.isEmpty()) 
				{
					cardNoModel.setCardNo(String.valueOf(map.get("card")));
					if (map.get("token") != null && StringUtils.isNoneEmpty(map.get("token").toString())) {
						List<CardTokenModel> cardList = Arrays
								.asList(new Gson().fromJson(map.get("token").toString(), CardTokenModel[].class));
						Collections.sort(cardList);
						cardNoModel.setTokenCard(cardList.get(0).getCardToken());
						cardNoModel.setCreatedDate(cardList.get(0).getCreatedDate());
					} else {
						cardNoModel.setTokenCard("");
						cardNoModel.setCreatedDate("");
					}
					cardNoModel.setResponseCode("00");
					cardNoModel.setResponseDesc("Success");
				} else {
					cardNoModel.setCardNo("");
					cardNoModel.setTokenCard("");
					cardNoModel.setCreatedDate("");
					cardNoModel.setResponseCode("99");
					cardNoModel.setResponseDesc("Error : Clear Card Number");
				}
			} else {
				cardNoModel.setResponseCode("11");
				cardNoModel.setResponseDesc("Invalid Password");
			}
		} catch (Exception e) {
			
			cardNoModel.setResponseCode("99");
			cardNoModel.setResponseDesc("Internal Error");
		}
		return cardNoModel;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteBin", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteBin(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteBin(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateBin", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateBin(@RequestBody BinModel binModel) 
	{
		String action = "UpdateBin";
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateBin(binModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
			
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteBranch(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteBranch(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/getNetworkScheme", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean getNetworkScheme(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			responseBean.setMessage(configurationService.getNetworkScheme(id));
			responseBean.setFlag(configurationService.getBinFlag(id));
			responseBean.setOutResponseCode("00");
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateBranch(@RequestBody BranchTypeModel branchTypeModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateBranch(branchTypeModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteBranchCode", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteBranchCode(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteBranchCode(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateBranchCode", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateBranchCode(@RequestBody BranchCodeModel branchCodeModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateBranchCode(branchCodeModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteAccount(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteAccount(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateAccount(@RequestBody AccountTypeModel accountTypeModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateAccount(accountTypeModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteAddress(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteAddress(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateAddress(@RequestBody AddressTypeModel addressTypeModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateAddress(addressTypeModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteCardType", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteCardType(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteCardType(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/getCardTypeMCC", method = RequestMethod.POST, produces = "application/json")
	public List<KeyValueBean> getCardTypeMCC(@RequestParam(value = "id") String id) {
		List<KeyValueBean> list = null;
		try {
			list = configurationService.getCard_Type_MCC(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/getCardMCC", method = RequestMethod.POST, produces = "application/json")
	public List<KeyValueBean> getCardMCC(@RequestParam(value = "id") String id) {
		List<KeyValueBean> list = null;
		try {
			list = configurationService.getCard_MCC(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/updateCardType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateCardType(@RequestBody CardTypeModel cardTypeModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateCardType(cardTypeModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Not any changes found for update !");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteCardTemplate", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteCardTemplate(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteCardTemplate(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateCardTemplate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateCardTemplate(@RequestBody CardTemplateModel cardTemplateModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateCardTemplate(cardTemplateModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteCardPlastic", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteCardPlastic(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteCardPlastic(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateCardPlastic", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateCardPlastic(@RequestBody CardPlasticModel cardPlasticModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateCardPlastic(cardPlasticModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteEmail", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteEmail(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteEmail(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateEmail", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateEmail(@RequestBody EmailTypeModel emailTypeModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateEmail(emailTypeModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteNCMC", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteNCMC(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteNCMC(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateNCMC", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateNCMC(@RequestBody NcmcServiceModel ncmcServiceModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateNCMC(ncmcServiceModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteKey", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteKey(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteKey(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/updateKey", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateKey(@RequestBody KeyConfigModel keyConfigModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateKey(keyConfigModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}

	@ResponseBody
	@RequestMapping(value = "/getStateList", method = RequestMethod.POST, produces = "application/json")
	public List<StateModel> getStateList(@RequestParam(value = "id") String id) {
		try {
			return configurationService.getStateList(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getCityList", method = RequestMethod.POST, produces = "application/json")
	public List<CityModel> getCityList(@RequestParam(value = "id") String id) {
		try {
			return configurationService.getCityList(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/updateAccountFlag", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean updateAccountFlag(@RequestBody AccountRequest accountRequest) {
		try 
		{
			return clientService.addAccount(accountRequest);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			ResponseBean bean = new ResponseBean();
			bean.setOutResponseCode("99");
			bean.setMessage("Internal Error");
			return bean;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addDocument", method = RequestMethod.POST, produces = "application/json")
	public DocumentMaintainanceResponse addDocument(
			@RequestBody DocumentMaintainanceRequest documentMaintainanceRequest) {
		try {
			return clientService.addDocument(documentMaintainanceRequest);
		} catch (Exception e) {
			e.printStackTrace();
			DocumentMaintainanceResponse bean = new DocumentMaintainanceResponse();
			bean.setOutResponseCode("99");
			bean.setMessage("Internal Error");
			return bean;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean addDocument(@RequestBody GroupBean groupBean) 
	{
		try 
		{
			ResponseBean responseBean = new ResponseBean();
			if (roleService.insertGroup(groupBean) > 0) 
			{
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			}
			else 
			{
				responseBean.setOutResponseCode("99");
				responseBean.setMessage("Group Already Exists!");
			}
			return responseBean;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			ResponseBean bean = new ResponseBean();
			bean.setOutResponseCode("99");
			bean.setMessage("Internal Error");
			return bean;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getNCMCListType", method = RequestMethod.POST, produces = "application/json")
	public Object getNCMCListType(@RequestBody NcmcServiceModel ncmcServiceModel) {
		try {
			List<KeyValueBean> list = configurationService.getncmcList(ncmcServiceModel.getStrCardType());
			List<NcmcServiceModel> data = configurationService.getNcmcCode();
			for (KeyValueBean list1 : list) {
				NcmcServiceModel keyValueBean = data.stream().filter(x -> list1.getLkpkey().equals(x.getStrNcmcID()))
						.findAny().orElse(null);
				if (keyValueBean != null) {
					keyValueBean.setStrSelectID("selected");
				}
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseBean bean = new ResponseBean();
			bean.setOutResponseCode("99");
			bean.setMessage("Internal Error");
			return bean;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getBatchData", method = RequestMethod.POST, produces = "application/json")
	public List<KeyValueBean> getBatchData(@RequestParam(value = "id") String id) {
		try {
			return clientService.getBatchData(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/processBatchData", method = RequestMethod.POST, produces = "application/json")
	public void processBatchData(@RequestParam(value = "id") String id) {
		try {
			clientService.processBatchData(clientService.getBatchData(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getCountBatch", method = RequestMethod.POST, produces = "application/json")
	public Integer getCountBatch(@RequestParam(value = "id") String id) {
		int count = 0;
		try {
			count = configurationService.getBatchUpdateCount(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@ResponseBody
	@RequestMapping(value = "/addCardTokenConfig", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean addCardTokenConfig(@RequestBody CardTokenModel cardTokenModel) {
		ResponseBean responseBean = new ResponseBean();
		String methodName = "addCardTokenConfig";
		try {
			int count = 0;
			if (StringUtils.isEmpty(cardTokenModel.getCardNo()) && cardTokenModel.getStrDemandFlag() == 0) {
				count = configurationService.addCardToken(cardTokenModel);
			} else {
				count = 1;
			}
			Object response = configurationService.addCardScheduler(cardTokenModel);
			JsonObject jsonObject = new Gson().fromJson(response.toString(), JsonObject.class);
			if (jsonObject != null) {
				responseBean.setOutResponseCode(jsonObject.get("outResponseCode").getAsString());
				responseBean.setMessage(jsonObject.get("message").getAsString());
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("API Calling Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
			loggerUtil.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return responseBean;
	}

	@RequestMapping(value = "/addMobileTokenConfig", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean addMobileTokenConfig(@RequestBody MobileTokenModel mobileTokenModel) {
		ResponseBean responseBean = new ResponseBean();
		String methodName = "addMobileTokenConfig";
		try 
		{
			Object response = configurationService.addMobileScheduler(mobileTokenModel);
			JsonObject jsonObject = new Gson().fromJson(response.toString(), JsonObject.class);
			if (jsonObject != null) 
			{
				responseBean.setOutResponseCode(jsonObject.get("outResponseCode").getAsString());
				responseBean.setMessage(jsonObject.get("message").getAsString());
			}
			else 
			{
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("API Calling Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
			loggerUtil.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return responseBean;
	}
	
	//Added by Sunny Soni for getting account number list Start
	@ResponseBody
    @RequestMapping(value = "/getAccountNumberList", method = RequestMethod.POST, produces = "application/json")
    public List<AccountNumberModel> getAccountNumberList(@RequestParam(value = "accountType") String accountType) {
        try {
            return configurationService.getAccounNumberListBasedOnAccountType(accountType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	//Added by Sunny Soni for getting account number list End
	
	
	
	@RequestMapping(value = "/getCardTypList", method = RequestMethod.POST)
	public ResponseEntity<?> getCardTypeBasedOnParticipntId(@RequestBody CardTypeModel cardTypeModel)
	{
		ProcessResponse processWebResponse = new ProcessResponse();
		try 
		{
			List<CardTypeModel> cardtypelistData = configurationService.getCardTypeBasedOnParticipantId(cardTypeModel);
			if(cardtypelistData != null && cardtypelistData.size() > 0)
			{
				processWebResponse.setCode("S0000");
				processWebResponse.setStatus("Success");
				processWebResponse.setMessage("Successfully Retrieved.");
				processWebResponse.setCardTypeList(cardtypelistData);
			}
			else
			{
				processWebResponse.setCode("E0000");
				processWebResponse.setStatus("Failed");
				processWebResponse.setMessage("Data not Found ");
			}
			return ResponseEntity.ok(processWebResponse);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(processWebResponse);
	}
	
	
	
	
	
	
}
