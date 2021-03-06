package cn.cstqb.exam.testmaker.actions.questionLanguage;

import cn.cstqb.exam.testmaker.configuration.Constants;
import cn.cstqb.exam.testmaker.entities.QuestionLanguage;

import com.google.common.collect.Lists;

public class UpdateQuestionLanguageAction extends BaseQuestionLanguageAction {

	@Override
	public void validateInput() {
		super.validateInput();
		if (questionLanguage.getId() == null || questionLanguage.getId() < 0){
			addActionError(getText("error.entity.invalid", Lists.newArrayList(QuestionLanguage.class.getSimpleName())));
		}
	};

	@Override
	protected String executeImpl() throws Exception {
		if (!questionLanguageService.exists(questionLanguage)) {
			addActionError(getText("error.entity.not.exist", Lists.newArrayList(questionLanguage)));
			return Constants.RESULT_NOT_FOUND;
		}
		logger.debug("UpdateQuestionLanguage.executeImpl: loading persisted version for this question language: {}", questionLanguage);
		QuestionLanguage persisted = questionLanguageService.findQuestionLanguage(questionLanguage);
		if (persisted.equals(questionLanguage)) {
			addActionError(getText("error.entity.notModified", Lists.newArrayList(questionLanguage)));
			return Constants.RESULT_NOT_MODIFIED;
		}
		questionLanguageService.saveOrUpdate(questionLanguage);
		return null;
	}

}
