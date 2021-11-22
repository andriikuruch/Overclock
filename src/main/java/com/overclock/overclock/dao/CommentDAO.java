package com.overclock.overclock.dao;

import com.overclock.overclock.model.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentDAO {
    static final String SQL_SELECT_COMMENTS_ID_BY_ASSEMBLY_ID =
            "SELECT OBJECT_ID COMMENT_ID FROM OBJECTS " +
                    "WHERE PARENT_ID = ? AND OBJECT_TYPE_ID = 8"; /* Comment */

    static final String SQL_SELECT_ALL_COMMENTS_BY_ASSEMBLY_ID =
            "SELECT COMMENTS.OBJECT_ID ID, MESSAGES.VALUE MESSAGE, " +
                    "DATES.DATE_VALUE DATE_OF_COMMENT, USERS.NAME AUTHOR " +
                    "   FROM OBJECTS COMMENTS, OBJECTS ASSEMBLIES, OBJECTS USERS, " +
                    "   OBJREFERENCE REF, ATTRIBUTES MESSAGES, ATTRIBUTES DATES " +
                    "        WHERE COMMENTS.OBJECT_TYPE_ID = 8 " + /* Comment */
                    "        AND ASSEMBLIES.OBJECT_TYPE_ID = 1 " + /* Assembly */
                    "        AND USERS.OBJECT_TYPE_ID = 7 " + /* Author */
                    "        AND ASSEMBLIES.OBJECT_ID = ?" +
                    "        AND REF.ATTR_ID = 44 " + /* Author id */
                    "        AND REF.OBJECT_ID = USERS.OBJECT_ID " +
                    "        AND REF.REFERENCE = COMMENTS.OBJECT_ID " +
                    "        AND MESSAGES.ATTR_ID = 43 " + /* Message */
                    "        AND DATES.ATTR_ID = 45 " + /* Date of comment */
                    "        AND MESSAGES.OBJECT_ID = COMMENTS.OBJECT_ID " +
                    "        AND DATES.OBJECT_ID = COMMENTS.OBJECT_ID " +
                    "        AND COMMENTS.PARENT_ID = ASSEMBLIES.OBJECT_ID " +
                    "   ORDER BY DATE_OF_COMMENT";

    static final String SQL_SELECT_LIMITED_LIST_OF_COMMENTS_BY_ASSEMBLY_ID =
            "SELECT * FROM (" + SQL_SELECT_ALL_COMMENTS_BY_ASSEMBLY_ID + ") WHERE rownum <= ?";

    boolean save(Comment comment, BigInteger assemblyId, BigInteger authorId);
    List<Comment> getLimitedListOfCommentsByAssemblyId(BigInteger assemblyId, BigInteger limit);
    List<Comment> getAllCommentsByAssemblyId(BigInteger assemblyId);
    boolean delete(BigInteger id);
    boolean deleteAllCommentsByAssemblyId(BigInteger assemblyId);
}
